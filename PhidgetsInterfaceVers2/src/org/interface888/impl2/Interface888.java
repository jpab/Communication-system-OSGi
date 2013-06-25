package org.interface888.impl2;


import java.util.concurrent.ConcurrentHashMap;

import org.interface888.listener2.AttachInterface888;
import org.interface888.listener2.DetachInterface888;
import org.interface888.listener2.Interface888Change;
import org.osgi.framework.BundleContext;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.Phidget;
import com.phidgets.PhidgetException;

public class Interface888 extends Thread{
	public static ConcurrentHashMap<Integer, PhidgetDevice> phidgets;
	private BundleContext bc = null; 
	
	public Interface888(BundleContext b) {
		bc = b;
	}

	public void start(){
		phidgets = new ConcurrentHashMap<Integer,PhidgetDevice>();
		try {
			InterfaceKitPhidget itk;
			System.out.println(Phidget.getLibraryVersion());
			itk = new InterfaceKitPhidget();
			itk.openAny();
			if(itk.isAttached()==false){
				System.out.println("[Bundle-Interface888] Wait for attach");
				itk.waitForAttachment(1000);
				System.out.println("[Bundle-Interface888] Attached");
			}
			initSensors(itk);
			itk.addSensorChangeListener(new Interface888Change(bc));
			itk.addAttachListener(new AttachInterface888(itk, bc));
			itk.addDetachListener(new DetachInterface888(itk, bc));
			System.out.println( Double.valueOf((itk.getSensorValue(1)-200)/4) );
			//Phidget p = new Phidget();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initSensors(InterfaceKitPhidget itk) throws PhidgetException {
		//0
		TouchSensor tmpT = new TouchSensor(itk, bc);
		phidgets.put(new Integer(0), tmpT);
		tmpT.regist();
		
		//1
		if(itk.getSensorValue(1) > 10){
			TemperatureSensor tmp = new TemperatureSensor(itk, bc);
			phidgets.put(new Integer(1), tmp);
			tmp.regist();
		}
		//2
		if(itk.getSensorValue(2) > 10){
			PrecisionLightSensor tmpPL = new PrecisionLightSensor(itk, bc);
			phidgets.put(new Integer(2), tmpPL);
			tmpPL.regist();
		}
		//3
		PresenceSensor tmpP = new PresenceSensor(itk, bc);
		phidgets.put(new Integer(3), tmpP);
		tmpP.regist();
		
		//4
		if(itk.getSensorValue(4) !=0){
			SliderSensor tmpS = new SliderSensor(itk, bc);
			phidgets.put(new Integer(4), tmpS);
			tmpS.regist();
		}
	/*	if(itk.getSensorValue(5) > 10){
			TemperatureSensor tmp = new TemperatureSensor(itk, bc);
			phidgets.put(new Integer(5), tmp);
			tmp.regist();
		}*/
		//6
		if(itk.getSensorValue(6) != 0){
			RotationSensor tmpR = new RotationSensor(itk, bc);
			phidgets.put(new Integer(6), tmpR);
			tmpR.regist();
		}
		//7
		VibrationSensor tmpVB = new VibrationSensor(itk, bc);
		phidgets.put(new Integer(7), tmpVB);
		tmpVB.regist();
	}

}
