package org.example;

import org.deviceservice.api.DeviceService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


public class DevicesServiceTrackerCustomizer implements ServiceTrackerCustomizer {

	private final BundleContext context;
	private MyThread thread;
	
	
	public DevicesServiceTrackerCustomizer(BundleContext context) {
		this.context = context;
	}

	@Override
	public Object addingService(ServiceReference reference) {
		DeviceService service = (DeviceService) context.getService(reference);
		thread = new MyThread(service);
		thread.start();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		// removedService(reference, service);
		// addingService(reference);
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		context.ungetService(reference);
		System.out.println("How sad. Service for device is gone");
		thread.stopThread();
}

}
