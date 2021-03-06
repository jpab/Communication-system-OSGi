package org.interface888.impl;


import org.deviceservice.api.DeviceService;
import org.deviceservice.controller.api.DeviceController;
import org.interface888.listener.AttachInterface888;
import org.interface888.listener.DetachInterface888;
import org.interface888.listener.Interface888Change;
import org.interface888.services.AmbientSense;
import org.interface888.services.Sensitivity;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.Phidget;
import com.phidgets.PhidgetException;

public class Interface888 extends Thread{
	public static final int TOUCH = 0;
	public static final int TEMPERATURE = 1;
	public static final int PRECISIONLIGHT = 2;
	public static final int PRESENCE = 3;
	public static final int SLIDER = 4;
	public static final int ROTATION = 6;
	public static final int VIBRATION = 7;
	public static final String TOUCHNAME = "Touch";
	public static final String TEMPERATURENAME = "Temperature";
	public static final String PRECISIONLIGHTNAME = "PrecisionLight";
	public static final String PRESENCENAME = "Presence";
	public static final String SLIDERNAME = "Slider";
	public static final String ROTATIONNAME = "Rotation";
	public static final String VIBRATIONNAME = "Vibration";
	public static final String DATARATE = "DataRate";
	public static final String SENSITIVITY = "Sensitivity";
	
	private BundleContext bc = null; 
	private AmbientSense as;
	private Sensitivity ss = null;
	private ServiceRegistration ambientSenseReg =null;
	private ServiceRegistration sensitivityReg =null;
	private InterfaceKitPhidget itk = null;
	
	public Interface888(BundleContext b) {
		bc = b;
	}

	public void start(){
		//phidgets = new ConcurrentHashMap<Integer,PhidgetDevice>();
		try {
			System.out.println(Phidget.getLibraryVersion());
			itk = new InterfaceKitPhidget();
			itk.openAny();
			if(itk.isAttached()==false){
				System.out.println("[Bundle-Interface888] Wait for attach");
				itk.waitForAttachment(1000);
				System.out.println("[Bundle-Interface888] Attached");
			}
			if(itk.isAttached()==true){
				System.out.println("[Interface-888]Regista Servi�os");
				registServices();
			}
			itk.addSensorChangeListener(new Interface888Change(this));
			itk.addAttachListener(new AttachInterface888(this));
			itk.addDetachListener(new DetachInterface888(this));
			System.out.println( Double.valueOf((itk.getSensorValue(1)-200)/4) );
			//Phidget p = new Phidget();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registServices() throws PhidgetException {
		as = new AmbientSense(itk);
		
		ambientSenseReg = bc.registerService(DeviceService.class.getName(), as , null);
		System.out.println("Registered: "+as.getName());
		
		ss = new Sensitivity(itk);		
		sensitivityReg = bc.registerService(DeviceController.class.getName(), ss , null);
		System.out.println("Registered: "+ss.getName());
		
	}

	public void changeAmbient(int index, int value) {
		if(as!=null){
			as.updateAmbient(index, value);
		}
		
	}

	public void unregistServices() {
		ambientSenseReg.unregister();
		sensitivityReg.unregister();
	}

}
