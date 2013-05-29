package org.RFIDPh.impl;




import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;



import com.phidgets.TemperatureSensorPhidget;

public class Activator implements BundleActivator{

	BundleContext bc= null;
	TemperatureSensorPhidget tempsensor = null;
	RFIDPh intr = null;
	
	@Override
	public void start(BundleContext context) throws Exception {
		bc = context;
		intr = new RFIDPh(bc);
		intr.start();
		intr.regist();
	}

	
	@Override
	public void stop(BundleContext context) throws Exception {
		bc = null;
		intr.unregist();
	}

}
