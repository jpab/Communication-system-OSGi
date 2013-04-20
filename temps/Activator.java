package org.phidgets.Temperature.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.phidgets.Phidget;
import com.phidgets.TemperatureSensorPhidget;

public class Activator implements BundleActivator {
	PhidgetTemperature pt = new PhidgetTemperature();

	@Override
	public void start(BundleContext context) throws Exception {
				pt.start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		pt.join();
	}

}
