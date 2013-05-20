package org.precisionlight.impls;

import java.util.HashMap;
import java.util.Set;

import org.deviceservice.sensing.api.DeviceSensing;

import com.phidgets.Phidget;

public class ShowPrecisionLight implements DeviceSensing{

	Phidget sensor;
	HashMap<String,String> prop;
	
	
	public ShowPrecisionLight(Phidget sensor2) {
		sensor = sensor2;
		prop = new HashMap<String,String>();
		prop.put("LightPrecision","0");
	}

	@Override
	public String getDescription() {
		return "This service show the light precision of the sensor phidget";
	}

	@Override
	public String getName() {
		return "Light Precision";
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