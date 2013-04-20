package org.phidgets.Temperature.impl;

import java.io.IOException;

import aQute.bnd.annotation.component.*;
import org.phidgets.temperature.api.ShowTemperature;
import com.phidgets.*;
import com.phidgets.event.*;

@Component
public class PhidgetTemperature extends Thread implements ShowTemperature{
	TemperatureSensorPhidget tempsensor;
	
	public void run (String st) throws PhidgetException, IOException{

		System.out.println(Phidget.getLibraryVersion());

		tempsensor = new TemperatureSensorPhidget();
		tempsensor.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae) {
				System.out.println("attachment of " + ae);
			}
		});
		tempsensor.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
			}
		});
		tempsensor.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		tempsensor.addTemperatureChangeListener(new TemperatureChangeListener()
		{
			public void temperatureChanged(TemperatureChangeEvent oe)
			{
				System.out.println(oe);
			}
		});

		tempsensor.openAny();
		System.out.println("waiting for TemperatureSensor attachment...");
		tempsensor.waitForAttachment();

		System.out.println("Serial: " + tempsensor.getSerialNumber());
		tempsensor.setTemperatureChangeTrigger(0, 0.1);
		System.out.println("trigger: " + tempsensor.getTemperatureChangeTrigger(0));
		System.out.println("Outputting events.  Input to stop.");
		System.in.read();
		System.out.print("closing...");
		tempsensor.close();
		tempsensor = null;
		System.out.println(" ok");
/*		if (false) {
			System.out.println("wait for finalization...");
			System.gc();
		}*/
	}
	
	@Override
	public String getTemperatureC() {
		try {
			return new Double(tempsensor.getAmbientTemperature()).toString();
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String getTemperatureF() {
		try {
			return new Double(tempsensor.getAmbientTemperature()).toString();
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
		return "";
	}

}