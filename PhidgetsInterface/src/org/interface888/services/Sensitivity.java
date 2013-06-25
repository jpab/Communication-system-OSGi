package org.interface888.services;

import java.util.HashMap;
import java.util.Observable;
import java.util.Set;
import java.util.StringTokenizer;

import org.deviceservice.controller.api.DeviceController;
import org.interface888.impl.Interface888;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;


public class Sensitivity extends Observable implements DeviceController{

	
	HashMap<String, String> values= null;
	int changed = 1;
	InterfaceKitPhidget itk = null;
	
	public Sensitivity(InterfaceKitPhidget interfaceph) {
		
		itk = interfaceph;
		values = new HashMap<String, String>();
		try {
			values.put(Interface888.DATARATE+" "+Interface888.TOUCHNAME, new Integer(itk.getDataRate(Interface888.TOUCH)).toString());
			values.put(Interface888.SENSITIVITY+" "+Interface888.TOUCHNAME, new Integer(itk.getSensorChangeTrigger(Interface888.TOUCH)).toString());
			values.put(Interface888.DATARATE+" "+Interface888.TEMPERATURENAME, new Integer(itk.getDataRate(Interface888.TEMPERATURE)).toString());
			values.put(Interface888.SENSITIVITY+" "+Interface888.TEMPERATURENAME, new Integer(itk.getSensorChangeTrigger(Interface888.TEMPERATURE)).toString());
			values.put(Interface888.DATARATE+" "+Interface888.PRECISIONLIGHTNAME, new Integer(itk.getDataRate(Interface888.PRECISIONLIGHT)).toString());
			values.put(Interface888.SENSITIVITY+" "+Interface888.PRECISIONLIGHTNAME, new Integer(itk.getSensorChangeTrigger(Interface888.PRECISIONLIGHT)).toString());
			values.put(Interface888.DATARATE+" "+Interface888.PRESENCENAME, new Integer(itk.getDataRate(Interface888.PRESENCE)).toString());
			values.put(Interface888.SENSITIVITY+" "+Interface888.PRESENCENAME, new Integer(itk.getSensorChangeTrigger(Interface888.PRESENCE)).toString());
			values.put(Interface888.DATARATE+" "+Interface888.SLIDERNAME, new Integer(itk.getDataRate(Interface888.SLIDER)).toString());
			values.put(Interface888.SENSITIVITY+" "+Interface888.SLIDERNAME, new Integer(itk.getSensorChangeTrigger(Interface888.SLIDER)).toString());
			values.put(Interface888.DATARATE+" "+Interface888.ROTATIONNAME, new Integer(itk.getDataRate(Interface888.ROTATION)).toString());
			values.put(Interface888.SENSITIVITY+" "+Interface888.ROTATIONNAME, new Integer(itk.getSensorChangeTrigger(Interface888.ROTATION)).toString());
			values.put(Interface888.DATARATE+" "+Interface888.VIBRATIONNAME, new Integer(itk.getDataRate(Interface888.VIBRATION)).toString());
			values.put(Interface888.SENSITIVITY+" "+Interface888.VIBRATIONNAME, new Integer(itk.getSensorChangeTrigger(Interface888.VIBRATION)).toString());
		
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//actualizado quando iniciado
	}

	@Override
	public String getDescription() {
		return "Change de Senstivity of the analog sensorsr";
	}

	@Override
	public String getName() {
		return "Senstivity Analog Sensors";
	}

	@Override
	public HashMap<String, String> getCollection() {
		return values;
	}

	@Override
	public Set<String> getProperties() {
		return values.keySet();
	}

	@Override
	public String getValue(String s) {
		return values.get(s);
	}

	@Override
	public void setValue(String s, String v) {
		changed = 1;
		StringTokenizer token = new StringTokenizer(s," ");
		String sensitivityType = token.nextToken();
		String sensor = token.nextToken();
		int indexSensor = -1;
		
		if(sensor.equals(Interface888.TOUCHNAME)){
			indexSensor = Interface888.TOUCH;
		}else if(sensor.equals(Interface888.TEMPERATURENAME)){
			indexSensor = Interface888.TEMPERATURE;
		}else if(sensor.equals(Interface888.PRECISIONLIGHTNAME)){
			indexSensor = Interface888.PRECISIONLIGHT;
		}else if(sensor.equals(Interface888.PRESENCENAME)){
			indexSensor = Interface888.PRESENCE;
		}else if(sensor.equals(Interface888.SLIDERNAME)){
			indexSensor = Interface888.SLIDER;
		}else if(sensor.equals(Interface888.ROTATIONNAME)){
			indexSensor = Interface888.ROTATION;
		}else if(sensor.equals(Interface888.VIBRATIONNAME)){
			indexSensor = Interface888.VIBRATION;
		}
		try {
			if(sensitivityType.equals(Interface888.DATARATE)){
				itk.setDataRate(indexSensor,new Integer(v).intValue());
			}else if(sensitivityType.equals(Interface888.SENSITIVITY)){
				itk.setSensorChangeTrigger(indexSensor,new Integer(v).intValue());
			}else{System.out.println("[Sensitivity] sensitivityType not exist");}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
		values.put(s, v);
	}

	@Override
	public int getChanged() {
		System.out.println("GETChangedChamado(Light) "+changed);
		int rt =changed;
		changed=0;
		return rt;
	}
	
	@Override
	public void setChanged(int changed) {
		System.out.println("SETChangedChamado(Light) "+changed);
		this.changed = changed;
	}
	
}
