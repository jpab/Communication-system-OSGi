package org.interface888.impl;


import java.util.HashMap;

import org.deviceservice.api.DeviceService;
import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.interface888.services.SensitivityPrecisionLight;
import org.interface888.services.ServicePrecisionLight;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.phidgets.InterfaceKitPhidget;

/*It just regist one service sensing that is able to sense intisity of the light
 * */
public class PrecisionLightSensor implements PhidgetDevice{
	
	HashMap<String,ServiceRegistration> services; 
	InterfaceKitPhidget itk = null;
	BundleContext bc = null;
	
	public PrecisionLightSensor(InterfaceKitPhidget phidget, BundleContext b){
		itk = phidget;
		bc =b;
	}
		
	public void regist(){

		System.out.println("[Precision-Light]Regista Servi�os");
		ServiceRegistration sraux;
		//regist Service Sense
		DeviceSensing ds = new ServicePrecisionLight();		
		sraux = bc.registerService(DeviceService.class.getName(), ds , null);
		services.put("PrecisionLight",sraux);
		//regist service sensibility
		DeviceController dsen = new SensitivityPrecisionLight(2,itk);		
		sraux = bc.registerService(DeviceController.class.getName(), dsen , null);
		services.put("Sensitivity",sraux);
		
		
		System.out.println("[Precision-Light]Registered: "+ds.getName());
	}

	public void unregist(){
		System.out.println("[Precision-Light]Retira Registos de Servi�os");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("[Precision-Light]unregisted "+ sr);
		}
		services.clear();	
	}	
	
	public void changed(int value){
		System.out.println("Mudou para" + value);
		DeviceSensing ds = (DeviceSensing) bc.getService(services.get(0).getReference());
		ds.setValue("PrecisionLight", new Integer(value).toString());
	}


}
