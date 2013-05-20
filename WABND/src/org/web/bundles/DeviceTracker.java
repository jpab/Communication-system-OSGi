package org.web.bundles;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/*
 * 
 * */
public class DeviceTracker {
	private DeviceBundleTracker bundleTracker;
	BundleContext bc=null;
	
	public DeviceTracker(BundleContext context){
		bc = context;
		System.out.println("[DeviceBundleTracker]Starting Bundle Tracker");
		int trackStates = Bundle.STARTING | Bundle.STOPPING | Bundle.RESOLVED | Bundle.INSTALLED | Bundle.UNINSTALLED;
		bundleTracker = new DeviceBundleTracker(bc, trackStates, null);
		bundleTracker.open();
	}

	public DeviceBundleTracker getBundleTracker() {
		return bundleTracker;
	}
	

	
}
