package org.interface888.impl2;


import java.util.HashMap;

import org.deviceservice.api.DeviceService;
import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.interface888.services2.SensitivityPrecisionLight;
import org.interface888.services2.SensitivityTemperature;
import org.interface888.services2.ServiceTemperature;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;


public class TemperatureSensor implements PhidgetDevice{
	
	HashMap<String,ServiceRegistration> services; 
	InterfaceKitPhidget itk = null;
	BundleContext bc = null;
	
	public TemperatureSensor(InterfaceKitPhidget phidget, BundleContext b){
		itk = phidget;
		bc =b;
		services = new HashMap<String,ServiceRegistration>();
	}
		
	public synchronized void regist() throws PhidgetException{
		System.out.println("[Bundle-Interface888]Regista Serviços");
		ServiceRegistration sraux;
		
		DeviceSensing ds = new ServiceTemperature(itk.getSensorValue(1));
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		services.put("TemperatureC",sraux);
		System.out.println("[Bundle-Interface888] Registered: "+ds.getName());
		
		DeviceController dsen = new SensitivityTemperature(1,itk);		
		sraux = bc.registerService(DeviceController.class.getName(), dsen , null);
		services.put("Sensitivity",sraux);
	}
	
	public synchronized void unregist(){
		System.out.println("[Bundle-Interface888]Retira Registos de Serviços");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("[Bundle-Interface888] Unregistered "+ sr);
		}
		services.clear();	
	}	
	
	public synchronized void changed(int value){
		System.out.println("[Bundle-Interface888] Mudou para" + value);
		DeviceSensing ds = (DeviceSensing) bc.getService(services.get("TemperatureC").getReference());
		double val = Double.valueOf((value-200)/4);
		ds.setValue("TemperatureCelsius", new Double(val).toString()+" ºC");
		
	}


}
