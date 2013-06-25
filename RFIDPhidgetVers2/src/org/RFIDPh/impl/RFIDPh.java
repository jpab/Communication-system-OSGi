package org.RFIDPh.impl;


import java.util.HashMap;

import org.RFIDPh.listeners.AttachRFID;
import org.RFIDPh.listeners.DetachRFID;
import org.RFIDPh.listeners.RFIDTagGainListener;
import org.RFIDPh.services.ControllObject;
import org.RFIDPh.services.RFIDSwitchService;
import org.device.action.api.ActionService;
import org.device.switchbtn.api.SwitchService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;

//Regist services
public class RFIDPh{
	
	static HashMap<String,ServiceRegistration> services; 
	static RFIDPhidget rph;
	static BundleContext bc;
	ControllObject co =null;
	
	public RFIDPh(BundleContext b) {
		bc =b;
		try {
			rph = new RFIDPhidget();
			rph.openAny();
			System.out.println("STATE RFID ATTACHMENT "+rph.isAttached());
			
			rph.waitForAttachment(5000);
			
			rph.addAttachListener(new AttachRFID(bc));
			rph.addDetachListener(new DetachRFID(bc));
			rph.addTagGainListener(new RFIDTagGainListener(this));
			services = new HashMap<String,ServiceRegistration>();
			regist();
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
		
	}
		
	public static void regist(){

		System.out.println("Regista Serviços");
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
	
	public static void unregist(){
		System.out.println("Retira Registos de Serviços");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("unregistered "+ sr);
		}
		services.clear();	
	}	
	
	public void deployObject(){
		co.deployService();
	}
}
