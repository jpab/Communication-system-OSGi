package org.advancedServo.listners;

import org.advancedServo.impl.AdvancedServoPh;
import org.osgi.framework.BundleContext;

import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;

public class DetachAdvancedServo implements DetachListener{

	BundleContext bc = null;
	
	public DetachAdvancedServo(BundleContext b){
		bc=b;
	}

	@Override
	public void detached(DetachEvent arg0) {
		// Desregista servi�os
		System.out.println("[Interface-888]Desregista Servi�os");
		AdvancedServoPh.unregist();

	}

}
