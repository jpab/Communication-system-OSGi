package org.interface888.impl;


import java.util.HashMap;

import org.deviceservice.api.DeviceService;
import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.interface888.services.SensitivityPrecisionLight;
import org.interface888.services.ServiceRotation;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;


public class RotationSensor implements PhidgetDevice{
	
	HashMap<String,ServiceRegistration> services; 
	InterfaceKitPhidget itk = null;
	BundleContext bc = null;
	
	public RotationSensor(InterfaceKitPhidget phidget, BundleContext b){
		itk = phidget;
		bc =b;
		services = new HashMap<String,ServiceRegistration>();
	}
	
	@Override	
	public void regist() throws PhidgetException{

		System.out.println("Regista Serviços");
		ServiceRegistration sraux;
		
		DeviceSensing ds = new ServiceRotation(itk.getSensorValue(6));
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		services.put("Rotation",sraux);
		System.out.println("Registered: "+ds.getName());
		
		DeviceController dsen = new SensitivityPrecisionLight(6,itk);		
		sraux = bc.registerService(DeviceController.class.getName(), dsen , null);
		services.put("Sensitivity",sraux);
		
	}
	
	@Override
	public void changed(int value){
		System.out.println("Mudou para" + value);
		DeviceSensing ds = (DeviceSensing) bc.getService(services.get(0).getReference());
		ds.setValue("Rotation", new Integer(value).toString());
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
