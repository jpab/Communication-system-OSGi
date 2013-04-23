package org.temperature.impls;


import java.util.Hashtable;
import org.deviceservice.api.DeviceService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.phidgets.TemperatureSensorPhidget;

public class Activator implements BundleActivator{

	BundleContext bc= null;
	Hashtable<String,Hashtable<String,String>> prop = null;
	TemperatureSensorPhidget tempsensor = null;
	
	
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
		Hashtable<String,ServiceRegistration> services = new Hashtable<String,ServiceRegistration>();
		prop = new Hashtable<String,Hashtable<String,String>>();
		ServiceRegistration sraux;
		
		fillprop();
		System.out.println("try open");
		tempsensor = new TemperatureSensorPhidget();
		tempsensor.openAny();
		System.out.println("waiting for TemperatureSensor attachment...");
		//tempsensor.waitForAttachment();
		
		/*regist all services when phidget attached*/
		for(String s : prop.keySet()){
			sraux = bc.registerService(DeviceService.class.getName(), new ShowTempratureCelsius(tempsensor), prop.get(s));
			services.put(s, sraux);
			System.out.println("Registered: "+s);
			
		}
		
		
		tempsensor.addAttachListener(new AttachListenerDevice(context, prop, services));
		tempsensor.addDetachListener(new DetachListenerDevice(services));
		tempsensor.addErrorListener(new ErrorListenerDevice());
		tempsensor.addTemperatureChangeListener(new TemperatureChangeListenerDevice(tempsensor));
		
				
	}

	
	private void fillprop() {
		//Set the propriaties for Celsius Temperature Service
		Hashtable<String,String> propaux = new Hashtable<String,String>();
		propaux.put("Name", "TemperaturaCelsius");
		prop.put(propaux.get("Name"),propaux);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		bc = null;
		
	}

}
