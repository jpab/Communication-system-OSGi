package org.exemple.impls;

import org.deviceservice.api.DeviceService;

import com.phidgets.PhidgetException;
import com.phidgets.TemperatureSensorPhidget;

public class ShowTempratureCelsius implements DeviceService{

	TemperatureSensorPhidget sensor;
	
	public ShowTempratureCelsius(TemperatureSensorPhidget tempsensor) {
		sensor = tempsensor;
	}

	@Override
	public String getDescription() {
		return "This service show the temprature of the sensor phidget";
	}

	@Override
	public String getName() {
		return "Ambient Celsius Temprature";
	}

	@Override
	public String getType() {
		return "Sensing";
	}

	@Override
	public String DeployService() {
		String result = null;
		try {
			result = (new Double(sensor.getAmbientTemperature())).toString();
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}