package org.web.services;

import org.device.action.api.ActionService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ActionServicesTrackerCustomizer implements ServiceTrackerCustomizer {
	BundleContext context = null;
	public ActionServicesTrackerCustomizer(BundleContext bc) {
		context=bc;
	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("[ActionServiceTracker]How good. Service for device registered");
		ActionService service = (ActionService) context.getService(reference);
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		System.out.println("[ActionServiceTracker]Hey. An service was modified");
		 removedService(reference, service);
		 addingService(reference);

	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		context.ungetService(reference);
		System.out.println("[ActionServiceTracker]How sad. Service for device is gone");
	}

}
