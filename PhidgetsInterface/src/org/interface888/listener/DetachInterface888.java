package org.interface888.listener;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;

public class DetachInterface888 implements DetachListener{

	InterfaceKitPhidget itk=null;
	
	public DetachInterface888(InterfaceKitPhidget interfacekit) {
		itk = interfacekit;
	}

	@Override
	public void detached(DetachEvent arg0) {
		// Desregista servi�os e para bundle
		
	}

}
