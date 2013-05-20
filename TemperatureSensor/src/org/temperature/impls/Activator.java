package org.temperature.impls;


import java.util.Hashtable;
import org.deviceservice.api.DeviceService;
import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.temperature.listeners.AttachListenerDevice;
import org.temperature.listeners.DetachListenerDevice;
import org.temperature.listeners.ErrorListenerDevice;
import org.temperature.listeners.TemperatureChangeListenerDevice;
import org.temperature.tracking.DataHandlerTrackerCustomizer;
import com.phidgets.TemperatureSensorPhidget;

public class Activator implements BundleActivator{

	BundleContext bc= null;
	TemperatureSensorPhidget tempsensor = null;
	Hashtable<String,ServiceRegistration> services = null;
	DeviceSensing ds;
	ServiceTracker st =  null;
	
	/*When the bundle is started, he put the sensing propriaties in the "prop" variable 
	 * each key of prop is the name of the service an each object is a hash of propriaties and their values
	 * Create the sensorTemperature instance
	 * Wait for the attachment of the device and then regist the services and save their ServiceRegistration
	 * Creat the listeners for Dettach, Attach, Error and TempratureChange 
	 * 
	 *  
	 * */
	@Override
	public void start(BundleContext context) throws Exception {
		bc = context;
		services = new Hashtable<String,ServiceRegistration>();

		ServiceRegistration sraux;
		
		System.out.println("try open");
		tempsensor = new TemperatureSensorPhidget();
		tempsensor.openAny();
		System.out.println("waiting for TemperatureSensor attachment...");
		//tempsensor.waitForAttachment(5000);
		
		/*regist all services when phidget attached*/
		ds = new ShowTempratureCelsius(tempsensor);
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		services.put(ds.getName(), sraux);
		System.out.println("Registered: "+ds.getName());
		
		/*Get handlerdata service to send information that values change*/
		st = new ServiceTracker(bc, DeviceSensing.class.getName(), new DataHandlerTrackerCustomizer(bc));
		st.open();
		
		
		tempsensor.addAttachListener(new AttachListenerDevice(context, services));
		tempsensor.addDetachListener(new DetachListenerDevice(services));
		tempsensor.addErrorListener(new ErrorListenerDevice());
		tempsensor.addTemperatureChangeListener( new TemperatureChangeListenerDevice(ds,st, services));
		
				
	}

	
	@Override
	public void stop(BundleContext context) throws Exception {
		bc = null;
		st.close();
	}

}
