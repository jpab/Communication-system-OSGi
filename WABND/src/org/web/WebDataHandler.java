package org.web;



import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import wabnd.datahandler.api.DataHandlerService;

public class WebDataHandler implements DataHandlerService{
	BundleContext bc;
	private Tracker tr;
	
	public WebDataHandler(BundleContext context){
		bc = context;
		tr = new Tracker(bc);
	}
	
	public Tracker getTr() {
		return tr;
	}

	public void ServiceChanged(ServiceRegistration sr){
		System.out.println("I Web recived that "+sr.getReference()+"has changed");
	}



	@Override
	public void setValue(String s, String v, ServiceRegistration sr) {
		System.out.println("I Web recived that "+s+"was changed to "+ v);
		Object o = bc.getService(sr.getReference());
		if (o.getClass().getName() == "DeviceSensing"){
			DeviceSensing ds = (DeviceSensing)o;
			//UPDATE in WEB

			System.out.println("I Web recived that "+ds.getName()+"was changed to "+ ds.getCollection().toString() +"SOURCE");
		}
	}
}
