package org.interface888.impl;


import java.util.HashMap;

import org.deviceservice.api.DeviceService;
import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.interface888.services.SensitivityPrecisionLight;
import org.interface888.services.ServiceSlider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;


public class SliderSensor implements PhidgetDevice{
	
	HashMap<String,ServiceRegistration> services; 
	InterfaceKitPhidget itk = null;
	BundleContext bc = null;
	
	public SliderSensor(InterfaceKitPhidget phidget, BundleContext b){
		itk = phidget;
		bc =b;
		services = new HashMap<String,ServiceRegistration>();
	}
		
	public void regist() throws PhidgetException{

		System.out.println("Regista Serviços");
		ServiceRegistration sraux;
		
		DeviceSensing ds = new ServiceSlider(itk.getSensorValue(4));
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		services.put("Slider",sraux);	
		System.out.println("Registered: "+ds.getName());
		
		DeviceController dsen = new SensitivityPrecisionLight(4,itk);		
		sraux = bc.registerService(DeviceController.class.getName(), dsen , null);
		services.put("Sensitivity",sraux);
	}
	
	public void unregist(){
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
		ds.setValue("Slider", new Integer(value).toString());
	}


}
