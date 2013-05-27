package org.RFIDPh.impl;


import java.util.HashMap;

import org.RFIDPh.services.ControllObject;
import org.RFIDPh.services.RFIDSwitchService;
import org.device.action.api.ActionService;
import org.device.switchbtn.api.SwitchService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;

//Regist services
public class RFIDPh extends Thread{
	
	HashMap<String,ServiceRegistration> services; 
	RFIDPhidget rph = null;
	BundleContext bc = null;
	ControllObject co =null;
	
	public RFIDPh(BundleContext b) {
		try {
			rph = new RFIDPhidget();
			rph.openAny();
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
		bc =b;
	}
		
	public void regist(){

		System.out.println("Regista Servi�os");
		ServiceRegistration sraux;
		
		SwitchService ds = new RFIDSwitchService(rph);
		sraux = bc.registerService(SwitchService.class.getName(), ds , null);
		services.put("Switches",sraux);
		
		ControllObject co = new ControllObject(bc);
		sraux = bc.registerService(ActionService.class.getName(), co , null);
		services.put("Controll",sraux);
		//co = new ControllObject(bc);
		
		System.out.println("Registered: "+ds.getName());
		System.out.println("Registered: "+co.getName());	
		
	}
	
	public void unregist(){
		System.out.println("Retira Registos de Servi�os");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("unregistered "+ sr);
		}
		services.clear();	
	}	
	
	public void deployObject(){
		co.deployObject();
	}
	/*
	public void changed(boolean value){
		System.out.println("Mudou para" + value);
		SwitchService ss = (SwitchService) bc.getService(services.get("Switches").getReference());
		ss.setState("Touch", new Boolean(value));
	}
	*/
}
