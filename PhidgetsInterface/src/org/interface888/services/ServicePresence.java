package org.interface888.services;

import java.util.HashMap;
import java.util.Set;

import org.deviceservice.sensing.api.DeviceSensing;


public class ServicePresence implements DeviceSensing{

	HashMap<String,String> prop;
	private int changed = 1;
	
	
	public ServicePresence() {
		prop = new HashMap<String,String>();
		prop.put("Presence","Detected");
	}

	@Override
	public String getDescription() {
		return "Presence Service detects if is present";
	}

	@Override
	public String getName() {
		return "Presence";
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
		changed = 1;
		prop.put(s, v);
		
	}

	@Override
	public void setChanged(int changed) {
		this.changed = changed;
	}

	@Override
	public int getChanged() {
		int rt =changed;
		changed=0;
		return rt;
	}
}