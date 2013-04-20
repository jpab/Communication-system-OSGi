package org.phidgets.Temperature.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.phidgets.Phidget;
import com.phidgets.TemperatureSensorPhidget;

public class Activator {
	static PhidgetTemperature pt = new PhidgetTemperature();

	public static void main(String args[]){
		pt.start();
		try {
			pt.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
