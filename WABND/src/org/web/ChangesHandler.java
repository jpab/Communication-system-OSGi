package org.web;

import org.osgi.framework.ServiceRegistration;

public class ChangesHandler {

	public ChangesHandler(){;}
	
	public void ServiceChanged(ServiceRegistration sr){
		System.out.println("I Web recived that "+sr.getReference().getProperty("Name")+"has changed");
		
	}
}
