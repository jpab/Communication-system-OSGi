package org.advancedServo.impl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator{
	BundleContext bc = null;
	 AdvancedServoPh intr =  null;
	 
	@Override
	public void start(BundleContext context) throws Exception {
		bc = context;
		intr = new AdvancedServoPh(bc);
		//AdvancedServoPh.regist();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		AdvancedServoPh.unregist();
	}

}
