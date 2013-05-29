package org.advancedServo.listners;

import org.advancedServo.impl.AdvancedServoPh;
import org.osgi.framework.BundleContext;

import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;

public class AttachAdvancedServo implements AttachListener{
	BundleContext bc = null;
	
	public AttachAdvancedServo(BundleContext b){
		bc=b;
	}
	@Override
	public void attached(AttachEvent arg0) {
		
		//regista serviços
		System.out.println("[Interface-888]Regista Serviços");
		AdvancedServoPh.regist();
		
	}

}
