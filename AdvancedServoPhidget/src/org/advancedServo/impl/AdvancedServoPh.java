package org.advancedServo.impl;

import java.util.HashMap;


import org.advancedServo.listners.AttachAdvancedServo;
import org.advancedServo.listners.DetachAdvancedServo;
import org.advancedServo.listners.DoorMovedListner;
import org.advancedServo.services.OpenDoorService;
import org.device.switchbtn.api.SwitchService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.phidgets.AdvancedServoPhidget;
import com.phidgets.PhidgetException;

public class AdvancedServoPh{

	static HashMap<String,ServiceRegistration> services; 
	static AdvancedServoPhidget asp = null;
	static BundleContext bc = null;
	
	public AdvancedServoPh(BundleContext b) {

		bc=b;
		try {
			asp = new AdvancedServoPhidget();
			asp.openAny();
			asp.waitForAttachment(2000);
			
			asp.addAttachListener(new AttachAdvancedServo(bc));
			asp.addDetachListener(new DetachAdvancedServo(bc));
			asp.addServoPositionChangeListener(new DoorMovedListner(this));
			services = new HashMap<String,ServiceRegistration>();
			regist();
			initPosition();
		} catch (PhidgetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initPosition() throws PhidgetException, InterruptedException {
		
		asp.setEngaged(0, false);
		asp.setPositionMin(0, 30);
		asp.setPositionMax(0, 152);
		asp.setEngaged(0, true);
		System.out.println("SERVO 2: ");
		Thread.sleep(1000);
		System.out.println("SERVO 1: ");
		asp.setEngaged(0, false);
	}

	public static void regist(){

		System.out.println("Regista Serviços");
		ServiceRegistration sraux;
		
		SwitchService ds = new OpenDoorService(asp);
		
		sraux = bc.registerService(SwitchService.class.getName(), ds , null);
		
		services.put("OpenDoor",sraux);
		
		System.out.println("Registered: "+ds.getName());	
		
	}
	
	public static void unregist(){
		System.out.println("Retira Registos de Serviços");
		for (String sr : services.keySet()){
			services.get(sr).unregister();
			System.out.println("unregistered "+ sr);
		}
	}

	public void deployService() {
		((SwitchService)bc.getService(services.get("OpenDoor").getReference())).setChanged(1);		
	}

}
