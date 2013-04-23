package org.example;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class DevicesServiceTracker extends ServiceTracker {
	
//	private final MovieListerImpl lister = new MovieListerImpl();
//	private int finderCount = 0;
//	private ServiceRegistration registration = null;
	
	public DevicesServiceTracker(BundleContext context) {
		super(context, MovieFinder.class.getName(), null);
	}
	
	
	private boolean registering = false; 
	public Object addingService(ServiceReference reference) { 
		MovieFinder finder = (MovieFinder) context.getService(reference); 
		lister.bindFinder(finder); 
 
		synchronized(this) { 
			finderCount ++; 
			if (registering) 
				return finder; 
			registering = (finderCount == 1); 
			if (!registering) 
				return finder; 
		} 
 
		ServiceRegistration reg = context.registerService( 
		MovieLister.class.getName(), lister, null); 
 
		synchronized(this) { 
			registering = false; 
			registration = reg; 
		} 
 
		return finder; 
	} 
