package org.interface888.impl;


import java.util.HashMap;

import org.deviceservice.api.DeviceService;
import org.deviceservice.sensing.api.DeviceSensing;
import org.interface888.services.ServiceVibration;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.phidgets.InterfaceKitPhidget;


public class VibrationSensor implements PhidgetDevice{
	
	HashMap<String,ServiceRegistration> services; 
	InterfaceKitPhidget ikp = null;
	BundleContext bc = null;
	
	public VibrationSensor(InterfaceKitPhidget phidget, BundleContext b){
		ikp = phidget;
		bc =b;
	}
		
	public void regist(){

		System.out.println("Regista Serviços");
		ServiceRegistration sraux;
		DeviceSensing ds = new ServiceVibration();
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		services.put("Vibration",sraux);
		System.out.println("Registered: "+ds.getName());
	}
	
	public void uregist(){
		System.out.println("Retira Registos de Serviços");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("unregistered "+ sr);
		}
		services.clear();	
	}	
	
	public void changed(int value){
		System.out.println("Mudou para" + value);
		DeviceSensing ds = (DeviceSensing) bc.getService(services.get(0).getReference());
		ds.setValue("Temperature", new Integer(value).toString());
	}


}
