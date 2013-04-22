package org.deviceservice.action.control.api;

import java.util.HashMap;
import java.util.Set;

import org.deviceservice.api.DeviceService;

public interface DeviceSensing extends DeviceService{
	/*Key is the name of the value*/
	HashMap<String,String> getCollection();
	Set<String> getProperties();
	String getValue(String s);
	
}