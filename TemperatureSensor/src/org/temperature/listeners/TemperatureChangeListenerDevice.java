package org.temperature.listeners;

import java.util.Hashtable;

import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import wabnd.datahandler.api.DataHandlerService;


import com.phidgets.event.TemperatureChangeEvent;
import com.phidgets.event.TemperatureChangeListener;


public class TemperatureChangeListenerDevice implements TemperatureChangeListener {

	DeviceSensing sensor;
	ServiceTracker st;
	Hashtable<String, ServiceRegistration> services;
	
	public TemperatureChangeListenerDevice(DeviceSensing ds, ServiceTracker s, Hashtable<String, ServiceRegistration> srvs) {
		sensor = ds;
		st = s;
		services =srvs;
	}

	@Override
	public void temperatureChanged(TemperatureChangeEvent tce) {
		System.out.println("TMP : Temperature Changed: "+ new Double((tce.getValue()-200)/4).toString());
		sensor.setValue("Temperature",new Double((tce.getValue()-200)/4).toString());
		DataHandlerService dhs = (DataHandlerService)st.getService();
		if (dhs != null){
			dhs.setValue("Temperature", new Double((tce.getValue()-200)/4).toString(), services.get(sensor.getName()));
		}
	}

}
