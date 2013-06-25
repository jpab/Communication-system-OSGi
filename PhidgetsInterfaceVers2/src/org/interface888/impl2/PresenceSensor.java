package org.interface888.impl2;


import java.util.HashMap;

import org.deviceservice.api.DeviceService;
import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.interface888.services2.SensitivityPrecisionLight;
import org.interface888.services2.SensitivityPresence;
import org.interface888.services2.ServicePresence;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;


public class PresenceSensor implements PhidgetDevice{
	
	HashMap<String,ServiceRegistration> services; 
	InterfaceKitPhidget itk = null;
	BundleContext bc = null;
	
	public PresenceSensor(InterfaceKitPhidget phidget, BundleContext b){
		itk = phidget;
		bc =b;
		services = new HashMap<String,ServiceRegistration>();
	}
		
	public void regist() throws PhidgetException{

		System.out.println("Regista Serviços");
		ServiceRegistration sraux;
		
		DeviceSensing ds = new ServicePresence(itk.getSensorValue(3));
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		System.out.println("SRAUX: "+sraux);
		services.put("Presence",sraux);
		System.out.println("Registered: "+ds.getName());
		
		DeviceController dsen = new SensitivityPresence(3,itk);		
		sraux = bc.registerService(DeviceController.class.getName(), dsen , null);
		services.put("Sensitivity",sraux);
	}
	

	
	public void changed(int value){
		System.out.println("Mudou para" + value);
		DeviceSensing ds = (DeviceSensing) bc.getService(services.get("Presence").getReference());
		if(value >500 ){
			ds.setValue("Presence", "Undetected");
		}else{
			ds.setValue("Presence","Detected");
		}
	}

	@Override
	public void unregist() {
		System.out.println("Retira Registos de Serviços");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("unregistered "+ sr);
		}
		services.clear();		
	}


}
