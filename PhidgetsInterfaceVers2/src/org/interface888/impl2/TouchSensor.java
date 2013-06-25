package org.interface888.impl2;


import java.util.HashMap;

import org.deviceservice.api.DeviceService;
import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.interface888.services2.SensitivityPrecisionLight;
import org.interface888.services2.SensitivityTouch;
import org.interface888.services2.ServiceTouch;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;


public class TouchSensor implements PhidgetDevice{
	
	HashMap<String,ServiceRegistration> services; 
	InterfaceKitPhidget itk = null;
	BundleContext bc = null;
	
	public TouchSensor(InterfaceKitPhidget phidget, BundleContext b){
		itk = phidget;
		bc =b;
		services = new HashMap<String,ServiceRegistration>();
	}
		
	public void regist() throws PhidgetException{

		System.out.println("Regista Servi�os");
		ServiceRegistration sraux;
		
		DeviceSensing ds = new ServiceTouch(itk.getSensorValue(0));
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		services.put("Touch",sraux);
		System.out.println("Registered: "+ds.getName());
		
		DeviceController dsen = new SensitivityTouch(0,itk);		
		sraux = bc.registerService(DeviceController.class.getName(), dsen , null);
		services.put("Sensitivity",sraux);
	}
	
	public void unregist(){
		System.out.println("Retira Registos de Servi�os");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("unregistered "+ sr);
		}
		services.clear();	
	}	
	
	public void changed(int value){
		System.out.println("Mudou para" + value);
		DeviceSensing ds = (DeviceSensing) bc.getService(services.get("Touch").getReference());
		if(value >500 ){
			ds.setValue("Touch", "Undetected");
		}else{
			ds.setValue("Touch","Detected");
		}
	}


}
