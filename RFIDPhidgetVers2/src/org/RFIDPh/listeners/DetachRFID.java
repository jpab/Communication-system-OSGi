package org.RFIDPh.listeners;

import org.RFIDPh.impl.RFIDPh;
import org.osgi.framework.BundleContext;

import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;

public class DetachRFID implements DetachListener{

	BundleContext bc = null;
	
	public DetachRFID(BundleContext b){
		bc=b;
	}

	@Override
	public void detached(DetachEvent arg0) {
		// Desregista serviços
		System.out.println("[Interface-888]Desregista Serviços");
		RFIDPh.unregist();

	}

}
