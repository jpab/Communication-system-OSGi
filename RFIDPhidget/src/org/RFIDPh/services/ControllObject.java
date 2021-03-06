package org.RFIDPh.services;

import org.RFIDPh.impl.OpenDoorTrackerCustomizer;
import org.device.action.api.ActionService;
import org.device.switchbtn.api.SwitchService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/*when the RFID's ids are indentificaded they deploy actuator */

public class ControllObject implements ActionService{
	ServiceTracker tracker =null;
	BundleContext bc=null;
	ServiceReference actuator = null;
	Class<SwitchService> c = SwitchService.class;
	
	public ControllObject(BundleContext b){
		bc = b; 
		tracker = new ServiceTracker(bc, SwitchService.class.getName(), new OpenDoorTrackerCustomizer(bc));
		actuator = tracker.getServiceReference();
		
	}

	@Override
	public void deployService() {
		System.out.println("Deploy Object");
		
	}

	@Override
	public ServiceReference[] getActuators() {
		
		return tracker.getServiceReferences();
	}

	@Override
	public String getDescription() {
		return "RFID Action when a Tag pass";
	}

	@Override
	public String getName() {
		return "RFID Tag action";
	}

	@Override
	public void setActuator(ServiceReference dc) {
		actuator =dc;
		
	}

	public Class<SwitchService> getC() {
		return c;
	}

	@Override
	public ServiceReference getActuator() {
		return actuator;
	}



}
