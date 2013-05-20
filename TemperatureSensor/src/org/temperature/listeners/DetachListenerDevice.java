package org.temperature.listeners;

import java.util.Hashtable;

import org.osgi.framework.ServiceRegistration;

import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;

/*When the device is dettached we unregist the service*/
public class DetachListenerDevice implements DetachListener{

	private Hashtable<String, ServiceRegistration> srvs;

	public DetachListenerDevice( Hashtable<String, ServiceRegistration> services) {

		srvs= services;
	}

	@Override
	public void detached(DetachEvent de) {
		System.out.println("Recive the envent of the detach");
		for(String s : srvs.keySet()){
			srvs.get(s).unregister();
		}
		srvs.clear();
	}

	
}
