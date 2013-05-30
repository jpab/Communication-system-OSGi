/*
 * Class que tem o registo de todos os bundles que represent�o dispositivos no sistema
 * */

package org.web;

import java.util.Hashtable;

import org.device.action.api.ActionService;
import org.device.switchbtn.api.SwitchService;
import org.deviceservice.api.DeviceService;
import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.web.bundles.DeviceTracker;
import org.web.services.SwitchServicesTrackerCustomizer;
import org.web.services.DevicesControllerTrackerCustomizer;
import org.web.services.DevicesServiceTrackerCustomizer;
import org.web.services.ActionServicesTrackerCustomizer;


public class Tracker {
	private static Hashtable<String,Bundle> bndstracked;
	private static Hashtable<String,ServiceReference> srvstracked;
	BundleContext bc = null;
	private static DeviceTracker dt = null;
	private static ServiceTracker st =null;
	private static ServiceTracker sct =null;
	private static ServiceTracker at =null;
	private static ServiceTracker ss =null;
	
	public Tracker(BundleContext b){
		bc = b;
		initBundlesTrack();
		initServicesTrack();
		initControllerServicesTrack();
		initSwitchServicesTrack();
		initActionServicesTrack();
	}
	
	private void initActionServicesTrack() {
		at = new ServiceTracker(bc, ActionService.class.getName(), new ActionServicesTrackerCustomizer(bc));
		at.open(true);
		System.out.println("[TRACKER] Number of actions tracked: "+at.size()+" Tracked operations "+ sct.getTrackingCount());
		
	}

	private void initSwitchServicesTrack() {
		ss = new ServiceTracker(bc, SwitchService.class.getName(), new SwitchServicesTrackerCustomizer(bc));
		ss.open(true);
		System.out.println("[TRACKER] Number of switch tracked: "+ss.size()+" Tracked operations "+ sct.getTrackingCount());
		
	}

	private void initControllerServicesTrack() {
		sct = new ServiceTracker(bc, DeviceController.class.getName(), new DevicesControllerTrackerCustomizer(bc));
		sct.open(true);
		System.out.println("[TRACKER] Number of controllers tracked: "+sct.size()+" Tracked operations "+ sct.getTrackingCount());
		
	}

	private void initBundlesTrack() {
		bndstracked =  new Hashtable<String,Bundle>();
		for(Bundle b :  bc.getBundles()){
			if(b.getHeaders().get("Device")!=null){
				bndstracked.put(b.getSymbolicName(), b);
				//System.out.println("Device:"+b.getSymbolicName() + "??"+ b.getHeaders().get("Device"));
			}
		}
		dt = new DeviceTracker(bc);
		dt.getBundleTracker().open();
		
	}

	private void initServicesTrack() {
		srvstracked =  new Hashtable<String,ServiceReference>();
		/*for(String s :  bndstracked.keySet()){
			System.out.println("[TRACKER]Getting services of bundle: " + bndstracked.get(s).toString());
			if(bndstracked.get(s).getRegisteredServices()!=null){
				for(ServiceReference sr : bndstracked.get(s).getRegisteredServices()){
					if(DeviceSensing.class.isInstance(bc.getService(sr))){
						srvstracked.put(((DeviceSensing)bc.getService(sr)).getName(), sr);
						System.out.println("SSSSSSSSRRVVVVVVV");
					}
				}
			}else{
				System.out.println("[TRACKER]This bundle dont have services registred " + bndstracked.get(s).toString());
			}
		}*/
		st = new ServiceTracker(bc, DeviceService.class.getName(), new DevicesServiceTrackerCustomizer(bc));
		st.open(true);
		System.out.println("[TRACKER] Number of services tracked: "+st.size()+" Tracked operations "+ st.getTrackingCount());
	}

	public static Bundle getBundle(String s){
		return bndstracked.get(s);
	}
	
	public static Hashtable<String, Bundle> getBndstracked() {
		return bndstracked;
	}

	public static Hashtable<String, ServiceReference> getSrvstracked() {
		return srvstracked;
	}

	public static DeviceTracker getDt() {
		return dt;
	}

	public static ServiceTracker getSt() {
		return st;
	}

	public static ServiceTracker getSct() {
		return sct;
	}

	public static void setSct(ServiceTracker sct) {
		Tracker.sct = sct;
	}

	public static ServiceTracker getAt() {
		return at;
	}

	public static void setAt(ServiceTracker at) {
		Tracker.at = at;
	}

	public static ServiceTracker getSs() {
		return ss;
	}

	public static void setSs(ServiceTracker ss) {
		Tracker.ss = ss;
	}
	
}
