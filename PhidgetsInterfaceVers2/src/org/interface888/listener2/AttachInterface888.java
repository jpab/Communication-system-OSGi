package org.interface888.listener2;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;

public class AttachInterface888 implements AttachListener{
	InterfaceKitPhidget itk=null;
	BundleContext bc = null;
	
	public AttachInterface888(InterfaceKitPhidget interfacekit, BundleContext b){
		itk = interfacekit;
		bc=b;
	}
	@Override
	public void attached(AttachEvent arg0) {
		//inicia bundle se estiver parado
		try {
			if(bc.getBundle().getState() ==  Bundle.STOPPING ){

				System.out.println("[Interface-888]Inicia Bundle");
				bc.getBundle().start();
			}
		} catch (BundleException e) {
			e.printStackTrace();
		}
		
		//regista serviços
		System.out.println("[Interface-888]Regista Serviços");
		//regista outputs e inputs
		
	}

}
