package org.web.services;

import org.deviceservice.controller.api.DeviceController;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


public class DevicesControllerTrackerCustomizer implements ServiceTrackerCustomizer {

	private final BundleContext context;
	
	
	public DevicesControllerTrackerCustomizer(BundleContext context) {
		this.context = context;
		System.out.println("[DeviceControllerTracker]Starting DevicesServiceTracker");
	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("[DeviceControllerTracker]How good. Service for device registered");
		DeviceController service = (DeviceController) context.getService(reference);
		//thread = new DeviceThread(service);
		//thread.start();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		System.out.println("[DeviceControllerTracker]Hey. An service was modified");
		 removedService(reference, service);
		 addingService(reference);
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		context.ungetService(reference);
		System.out.println("[DeviceControllerTracker]How sad. Service for device is gone");
		//thread.stopThread();
}

}
