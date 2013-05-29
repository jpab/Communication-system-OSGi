package org.RFIDPh.impl;




import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator{

	BundleContext bc= null;
	RFIDPh intr = null;
	
	@Override
	public void start(BundleContext context) throws Exception {
		bc = context;
		intr = new RFIDPh(bc);
		RFIDPh.regist();
	}

	
	@Override
	public void stop(BundleContext context) throws Exception {
		bc = null;
		RFIDPh.unregist();
	}

}
