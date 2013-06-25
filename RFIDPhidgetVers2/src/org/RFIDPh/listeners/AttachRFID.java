package org.RFIDPh.listeners;

import org.RFIDPh.impl.RFIDPh;
import org.osgi.framework.BundleContext;

import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;

public class AttachRFID implements AttachListener{
	BundleContext bc = null;
	
	public AttachRFID(BundleContext b){
		bc=b;
	}
	@Override
	public void attached(AttachEvent arg0) {
		
		//regista servi�os
		System.out.println("[Interface-888]Regista Servi�os");
		RFIDPh.regist();
		
	}

}
