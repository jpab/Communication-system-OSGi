package org.precisionlight.tracking;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import wabnd.datahandler.api.DataHandlerService;

public class DataHandlerTrackerCustomizer implements ServiceTrackerCustomizer {

	private final BundleContext context;
	DataHandlerService dhs;

	
	public DataHandlerTrackerCustomizer(BundleContext context) {
		this.context = context;
	}
	
	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("TMP : How good. Service for device dataH registered");
		DataHandlerService service = (DataHandlerService) context.getService(reference);

		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		System.out.println("TMP : Hey. An service datah was modified");
		 removedService(reference, service);
		 addingService(reference);
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		context.ungetService(reference);
		System.out.println("TMP : How sad. Service datah for device is gone");

	}

}
