package org.interface888.services2;


import java.util.HashMap;
import java.util.Set;



import org.deviceservice.sensing.api.DeviceSensing;


public class ServiceTemperature implements DeviceSensing{

	/**
	 * 
	 */
	HashMap<String,String> prop = null;
	private int changed = 1;
	

	public ServiceTemperature(int i) {
		prop = new HashMap<String,String>();
		prop.put("TemperatureCelsius",(new Integer(i).toString())+" ¼C");
	}

	@Override
	public String getDescription() {
		return "This service show the temperature of the sensor phidget";
	}

	@Override
	public String getName() {
		return "Ambient Celsius Temprature";
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
		changed =1;
		prop.put(s, v);

	}
	@Override
	public int getChanged() {
		int rt =changed;
		changed=0;
		return rt;
	}

	public void setChanged(int changed) {
		this.changed = changed;
	}
	

}