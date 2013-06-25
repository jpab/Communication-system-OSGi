package org.interface888.listener;

import org.interface888.impl.Interface888;
import org.osgi.framework.BundleContext;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;

public class DetachInterface888 implements DetachListener{

	InterfaceKitPhidget itk=null;
	BundleContext bc = null;
	Interface888 i888 = null;
	public DetachInterface888(InterfaceKitPhidget interfacekit, BundleContext b){
		itk = interfacekit;
		bc=b;
	}

	public DetachInterface888(Interface888 interface888) {
		i888 = interface888;
		
	}

	@Override
	public void detached(DetachEvent arg0) {
		// Desregista serviços
		System.out.println("[Interface-888]Desregista Serviços");
		i888.unregistServices();
	/*	if(Interface888.phidgets!=null){
			for(Integer pd : Interface888.phidgets.keySet()){
				Interface888.phidgets.get(pd).unregist();
			}
			Interface888.phidgets.clear();
		} */
		//para bundle
		/*
		try {
			if(bc.getBundle().getState() ==  Bundle.ACTIVE ){
				System.out.println("[Interface-888]Para Bundle");
				bc.getBundle().stop();
			}
		} catch (BundleException e) {
			e.printStackTrace();
		}
		*/
		
	}

}
