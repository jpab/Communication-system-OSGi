package org.exemple.consumer;

import org.deviceservice.api.DeviceService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {
	ServiceTracker st;
	@Override
	public void start(BundleContext context) throws Exception {
		
		st = new ServiceTracker(context, DeviceService.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference reference) {
                Object svc = super.addingService(reference);
                if (svc instanceof DeviceService) {
                    invokeService((DeviceService) svc);
                }
                return svc;
            }
        };
        st.open();
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		st.close();
		
	}

    void invokeService(DeviceService svc) {
        String input = "Joao";
        System.out.println("Invoking Service with input: " + input);
        System.out.println("  Result: " + svc.sayHello(input));
    }
}
