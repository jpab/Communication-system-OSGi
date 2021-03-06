package org.exemple.impls;


import java.util.Hashtable;

import org.deviceservice.api.DeviceService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.TemperatureChangeEvent;
import com.phidgets.event.TemperatureChangeListener;

public class Activator implements BundleActivator{

	BundleContext bc= null;
	@Override
	public void start(BundleContext context) throws Exception {
		bc = context;
		Hashtable<String,ServiceRegistration> services = new Hashtable<String,ServiceRegistration>();
		Hashtable<String,String> prop = new Hashtable<String,String>();
		ServiceRegistration sraux;
		prop.put("Name", "Temperatura �C");
		
		TemperatureSensorPhidget tempsensor;
		tempsensor = new TemperatureSensorPhidget();
		tempsensor.openAny();
		System.out.println("waiting for TemperatureSensor attachment...");
		tempsensor.waitForAttachment();
		sraux = bc.registerService(DeviceService.class.getName(), new ShowTempratureCelsius(tempsensor), prop);
		services.put("TemperaturaCelsius", sraux);
		
		
		tempsensor.addAttachListener(new AttachListenerDevice(context, prop, services));
		tempsensor.addDetachListener(new DetachListenerDevice(services));
		tempsensor.addErrorListener(new ErrorListenerDevice());
		tempsensor.addTemperatureChangeListener(new TemperatureChangeListenerDevice());
		
				
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		bc = null;
		
	}

}
