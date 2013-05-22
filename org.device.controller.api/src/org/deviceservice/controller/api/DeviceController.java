package org.deviceservice.controller.api;

import java.util.HashMap;
import java.util.Set;

import org.deviceservice.api.DeviceService;

public interface DeviceController extends DeviceService{
	/*Key is the name of the value*/
	//value its 0 to 100
	HashMap<String,String> getCollection();
	Set<String> getProperties();
	String getValue(String s);
	void setValue(String s, String v);
	int getChanged();
	void setChanged(int changed);
}