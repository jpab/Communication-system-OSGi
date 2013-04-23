package org.temperature.impls;

import java.util.Hashtable;

import org.deviceservice.api.DeviceService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.phidgets.PhidgetException;
import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;

/*To the AtachL sends the "context", "prop" and "services" to regist the services when its atached again.*/

public class AttachListenerDevice implements AttachListener{

	BundleContext bc;
	/*Sensing Values*/
	Hashtable<String, Hashtable<String, String>> prop;
	/*Registered Services*/
	Hashtable<String,ServiceRegistration> srs;
	
	public AttachListenerDevice(BundleContext bndc, Hashtable<String, Hashtable<String, String>> prop2, Hashtable<String,ServiceRegistration> services){
		bc=bndc;
		prop = prop2;
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
			for(String s: prop.keySet()){
				if(bc.getService(srs.get(s).getReference())!=null){
					srs.put(prop.get(s).get("Name"), bc.registerService(DeviceService.class.getName(), new ShowTempratureCelsius(tempsensor), prop.get(s)));		
				}
			}
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
	}

}