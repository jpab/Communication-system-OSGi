package org.test.plataform.services;

import java.util.HashMap;
import java.util.Set;

import org.deviceservice.controller.api.DeviceController;


public class ControllTest implements DeviceController{

	HashMap<String, String> values= null;
	int changed = 1;
	
	public ControllTest() {
		values = new HashMap<String, String>();
		values.put("Test1", "0");
	}

	@Override
	public String getDescription() {
		return "Test Controller";
	}

	@Override
	public String getName() {
		return "Test Conroller Service";
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
		values.put(s, v);
	}

	@Override
	public int getChanged() {
		int rt =changed;
		changed=0;
		return rt;
	}
	
	@Override
	public void setChanged(int changed) {
		this.changed = changed;
	}

}
