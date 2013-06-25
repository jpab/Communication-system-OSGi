package org.interface888.listener;

import org.interface888.impl.Interface888;

import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;

public class AttachInterface888 implements AttachListener{
	Interface888 i888 = null;
	
	public AttachInterface888(Interface888 interface888) {
		i888 = interface888;
	}
	@Override
	public void attached(AttachEvent arg0) {
		
		//inicia bundle se estiver parado
	/*	try {
			if(bc.getBundle().getState() ==  Bundle.STOPPING ){

				System.out.println("[Interface-888]Inicia Bundle");
				bc.getBundle().start();
			}
		} catch (BundleException e) {
			e.printStackTrace();
		}
		*/
		//regista serviços
		System.out.println("[Interface-888]Regista Serviços");
		try {
			i888.registServices();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//regista outputs e inputs
		
	}

}
