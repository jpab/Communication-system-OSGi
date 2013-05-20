package org.interface888.services;

import java.util.HashMap;
import java.util.Set;

import org.deviceservice.sensing.api.DeviceSensing;
import com.phidgets.TemperatureSensorPhidget;

public class ServicePrecisionLight implements DeviceSensing{

	HashMap<String,String> prop;
	
	
	public ServicePrecisionLight(TemperatureSensorPhidget tempsensor) {
		prop = new HashMap<String,String>();
		prop.put("PrecisionLight","0");
	}

	public ServicePrecisionLight() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		return "This service show the light precision of the sensor phidget";
	}

	@Override
	public String getName() {
		return "Precision Light";
	}

	@Override
	public HashMap<String, String> getCollection() {
		return prop;
	}

	@Override
	public Set<String> getProperties() {
		return prop.keySet();
	}

	@Override
	public String getValue(String s) {
		return prop.get(s);
	}

	@Override
	public void setValue(String s, String v) {
		prop.put(s, v);
		
	}

	@Override
	public int getChanged() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	public String DeployService() {
		String result = null;
		try {
			result = (new Double(sensor.getAmbientTemperature())).toString();
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
		return result;
	}
*/

}