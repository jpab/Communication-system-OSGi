package org.web.services;

import org.deviceservice.api.DeviceService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


public class DevicesServiceTrackerCustomizer implements ServiceTrackerCustomizer {

	private final BundleContext context;
	private DeviceThread thread;
	
	
	public DevicesServiceTrackerCustomizer(BundleContext context) {
		this.context = context;
		System.out.println("[DeviceServiceTracker]Starting DevicesServiceTracker");
	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("[DeviceServiceTracker]How good. Service for device registered");
		DeviceService service = (DeviceService) context.getService(reference);
		thread = new DeviceThread(service);
		thread.start();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		System.out.println("[DeviceServiceTracker]Hey. An service was modified");
		 removedService(reference, service);
		 addingService(reference);
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		context.ungetService(reference);
		System.out.println("[DeviceServiceTracker]How sad. Service for device is gone");
		thread.stopThread();
}

}
