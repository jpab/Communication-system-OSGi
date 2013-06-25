 package org.interface888.listener;

import org.interface888.impl.Interface888;
import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;

public class Interface888Change implements SensorChangeListener{
	Interface888 i888 =null;
	
	public Interface888Change(Interface888 i888aux) {
		i888 = i888aux;
	}

	@Override
	public void sensorChanged(SensorChangeEvent sce) {
		i888.changeAmbient(sce.getIndex(),sce.getValue());
	}
}
