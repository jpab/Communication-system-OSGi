package org.test.plataform.impl;

import java.util.HashMap;

import org.device.switchbtn.api.SwitchService;
import org.deviceservice.controller.api.DeviceController;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.test.plataform.services.ControllTest;
import org.test.plataform.services.TurnOnTest;

public class TestPlataform {

	BundleContext bc =  null;
	HashMap<String,ServiceRegistration> services; 
	
	public TestPlataform(BundleContext context) {
		bc =context;
		services = new HashMap<String,ServiceRegistration>();
		
	}

	public void regist() {
		//controller
		System.out.println("Regista Serviços");
		ServiceRegistration sraux;
		
		DeviceController dsen = new ControllTest();		
		sraux = bc.registerService(DeviceController.class.getName(), dsen , null);
		services.put("TestControll",sraux);
		
		SwitchService ds = new TurnOnTest();
		sraux = bc.registerService(SwitchService.class.getName(), ds , null);
		services.put("OpenTest",sraux);
		
	}

	public void unregist() {
		System.out.println("Retira Registos de Serviços");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("unregistered "+ sr);
		}
		services.clear();		
	}

}
