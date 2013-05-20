package org.web.services;

import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


public class DeviceSensingTrackerCustomizer implements ServiceTrackerCustomizer {

	private final BundleContext context;
	private DeviceThread thread;
	
	
	public DeviceSensingTrackerCustomizer(BundleContext context) {
		this.context = context;
		System.out.println("[DeviceSensingTracker]Starting DeviceSensingTracker");
	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("[DeviceSensingTracker]How good. Service for device registered");
		DeviceSensing service = (DeviceSensing) context.getService(reference);
		thread = new DeviceThread(service);
		thread.start();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		System.out.println("[DeviceSensingTracker]Hey. An service was modified");
		 removedService(reference, service);
		 addingService(reference);
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		context.ungetService(reference);
		System.out.println("[DeviceSensingTracker]How sad. Service for device is gone");
		thread.stopThread();
}

}
