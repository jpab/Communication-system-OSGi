package org.precisionlight.impls;


import java.util.Hashtable;
import org.deviceservice.api.DeviceService;
import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.precisionlight.listeners.AttachListenerDevice;
import org.precisionlight.listeners.DetachListenerDevice;
import org.precisionlight.listeners.ErrorListenerDevice;
import org.precisionlight.tracking.DataHandlerTrackerCustomizer;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.Phidget;
import com.phidgets.TemperatureSensorPhidget;

public class Activator implements BundleActivator{

	BundleContext bc= null;
	Phidget sensor = null;
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
		
		InterfaceKitPhidget itk;
		System.out.println(Phidget.getLibraryVersion());
		itk = new InterfaceKitPhidget();
		itk.openAny();
		itk.waitForAttachment();
		System.out.println( Double.valueOf((itk.getSensorValue(0)-200)/4) );
		
		
		
		bc = context;
		services = new Hashtable<String,ServiceRegistration>();

		ServiceRegistration sraux;
		
		System.out.println("try open");
		sensor = new TemperatureSensorPhidget();
		sensor.openAny();
		System.out.println("waiting for Light Precision attachment...");
		//sensor.waitForAttachment(5000);
		
		/*regist all services when phidget attached*/
		ds = new ShowPrecisionLight(sensor);
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		services.put(ds.getName(), sraux);
		System.out.println("Registered: "+ds.getName());
		
		/*Get handlerdata service to send information that values change*/
		st = new ServiceTracker(bc, DeviceSensing.class.getName(), new DataHandlerTrackerCustomizer(bc));
		st.open();
		
		
		sensor.addAttachListener(new AttachListenerDevice(context, services));
		sensor.addDetachListener(new DetachListenerDevice(services));
		sensor.addErrorListener(new ErrorListenerDevice());
				
	}

	
	@Override
	public void stop(BundleContext context) throws Exception {
		bc = null;
		st.close();
	}

}
