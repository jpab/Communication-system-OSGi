package org.temperature.listeners;

import java.util.Hashtable;

import org.deviceservice.api.DeviceService;
import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.temperature.impls.ShowTempratureCelsius;

import com.phidgets.PhidgetException;
import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;

/*To the AtachL sends the "context", "prop" and "services" to regist the services when its atached again.*/

public class AttachListenerDevice implements AttachListener{

	BundleContext bc;
	/*Sensing Values*/
	DeviceSensing device;
	/*Registered Services*/
	Hashtable<String,ServiceRegistration> srs;
	
	public AttachListenerDevice(BundleContext bndc, Hashtable<String,ServiceRegistration> services){
		bc=bndc;
		srs = services;
	}
	
	/*If the sensor is attached then service is registered(if is not already registered) */
	@Override
	public void attached(AttachEvent ae) {
		try {
			TemperatureSensorPhidget tempsensor;
			tempsensor = new TemperatureSensorPhidget();
			tempsensor.openAny();
			System.out.println("Recive Event Atached: waiting for TemperatureSensor attachment...");
			tempsensor.waitForAttachment();
			DeviceSensing ts = new ShowTempratureCelsius(tempsensor);
			srs.put(ts.getName(), bc.registerService(DeviceService.class.getName(), ts, null));		
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
	}

}