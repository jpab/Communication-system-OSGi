package org.example.tests;

import junit.framework.TestCase;

import org.deviceservice.api.DeviceService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ExampleTest extends TestCase {

    private final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

    public void testServiceRegistered() throws Exception {
        ServiceReference ref = context.getServiceReference(DeviceService.class.getName());
        assertEquals("provider", ref.getBundle().getHeaders().get("Bundle-Name"));
    }
    
    public void testServiceConsumed() throws Exception {
        ServiceReference ref = context.getServiceReference(DeviceService.class.getName());
        System.out.println("usingB: "+ ref.getUsingBundles());
        for (Bundle b : ref.getUsingBundles()) {
            if (b.getHeaders().get("Bundle-Name").equals("consumer")) {
                // good, it's consumed by the consumer bundle
                return;
            }
        }
        fail("The consumer bundle isn't consuming the service");
    }

}
