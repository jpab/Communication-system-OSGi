package org.interface888.services;

import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

import org.deviceservice.sensing.api.DeviceSensing;
import org.interface888.impl.Interface888;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;


public class AmbientSense extends Observable implements DeviceSensing {
	
	
	HashMap<String,String> prop;
	private int changed = 1;
	InterfaceKitPhidget itk =  null;
	
	public AmbientSense(InterfaceKitPhidget interfaceph) {
		prop = new HashMap<String,String>();
		try {
			itk =  interfaceph;
			updateAmbient(Interface888.TOUCH, itk.getSensorValue(Interface888.TOUCH));
			updateAmbient(Interface888.PRECISIONLIGHT, itk.getSensorValue(Interface888.PRECISIONLIGHT));
			updateAmbient(Interface888.PRESENCE, itk.getSensorValue(Interface888.PRESENCE));
			updateAmbient(Interface888.SLIDER, itk.getSensorValue(Interface888.SLIDER));
			updateAmbient(Interface888.ROTATION, itk.getSensorValue(Interface888.ROTATION));
			updateAmbient(Interface888.VIBRATION, itk.getSensorValue(Interface888.VIBRATION));
			updateAmbient(Interface888.TEMPERATURE, itk.getSensorValue(Interface888.TEMPERATURE));
			System.out.println("RegisteredENSTOU");
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		try {
			update(1,itk.getSensorValue(1));
			update(2,itk.getSensorValue(2));
			update(3,itk.getSensorValue(3));
			update(4,itk.getSensorValue(4));
			update(0,itk.getSensorValue(0));
			update(6,itk.getSensorValue(6));
			update(7,itk.getSensorValue(7));
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	}

	public void updateAmbient(int i, int sensorValue) {
		String value;
		try {
			switch(i){
				
			case Interface888.TOUCH : 
				if(sensorValue >500){
					value = "TOUCH UNDETECTED";
				}else{
					value = "TOUCH DETECTED";
				}
				setValue(Interface888.TOUCHNAME,value);
				break;
			case Interface888.TEMPERATURE :
				value = Double.valueOf((itk.getSensorValue(1)-200)/4)-20+"¼C";
				setValue(Interface888.TEMPERATURENAME,value);
				break;
			case Interface888.PRECISIONLIGHT : 
				setValue(Interface888.PRECISIONLIGHTNAME,new Integer(sensorValue).toString());
				break;
			case Interface888.PRESENCE : 
				setValue(Interface888.PRESENCENAME,new Integer(sensorValue).toString());
				break;
			case Interface888.SLIDER :
				setValue(Interface888.SLIDERNAME,new Integer(sensorValue).toString());
				break;
			//case 5 : 
		//		precisionLightSensor(index,sce);
	//			break;
			case Interface888.ROTATION : 
				setValue(Interface888.ROTATIONNAME,new Integer(sensorValue).toString());
				break;
			case Interface888.VIBRATION : 
				setValue(Interface888.VIBRATIONNAME,new Integer(sensorValue).toString());
				break;
			default :  throw new PhidgetException(i, "Index not found in the swich options");
			}
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public String getDescription() {
		return "Analog Sensors";
	}

	@Override
	public String getName() {
		return "Analog Senses";
	}

	@Override
	public HashMap<String, String> getCollection() {
		return prop;
	}

	@Override
	public Set<String> getProperties() {
		return prop.keySet();
	}

	@Override
	public String getValue(String s) {
		return prop.get(s);
	}

	@Override
	public void setValue(String s, String v) {
		changed = 1;
		prop.put(s, v);
		this.notifyObservers();
	}

	@Override
	public void setChanged(int changed) {
		this.changed = changed;
	}

	@Override
	public int getChanged() {
		int rt =changed;
		changed=0;
		return rt;
	}
}