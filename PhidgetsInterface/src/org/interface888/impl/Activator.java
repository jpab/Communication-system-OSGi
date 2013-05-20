package org.interface888.impl;




import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;



import com.phidgets.TemperatureSensorPhidget;

public class Activator implements BundleActivator{

	BundleContext bc= null;
	TemperatureSensorPhidget tempsensor = null;

	
	@Override
	public void start(BundleContext context) throws Exception {
		bc = context;
		Interface888 intr = new Interface888(bc);
		intr.start();
		//intr.join();
	//	services = new Hashtable<String,ServiceRegistration>();
		
		
		
	}

	
	@Override
	public void stop(BundleContext context) throws Exception {
		bc = null;
	//	st.close();
	}

}
