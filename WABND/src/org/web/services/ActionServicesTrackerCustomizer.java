package org.web.services;

import org.device.switchbtn.api.SwitchService;
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
		System.out.println("[SwitchServiceTracker]How good. Service for device registered");
		SwitchService service = (SwitchService) context.getService(reference);
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		System.out.println("[SwitchServiceTracker]Hey. An service was modified");
		 removedService(reference, service);
		 addingService(reference);

	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		context.ungetService(reference);
		System.out.println("[SwitchServiceTracker]How sad. Service for device is gone");
	}

}
