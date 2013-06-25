package org.interface888.services2;

import java.util.HashMap;
import java.util.Set;

import org.deviceservice.controller.api.DeviceController;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;


public class SensitivitySlider implements DeviceController{
	int index = -1;
	HashMap<String, String> values= null;
	int changed = 1;
	InterfaceKitPhidget itk = null;
	
	public SensitivitySlider(int i, InterfaceKitPhidget interfaceph) {
		itk = interfaceph;
		values = new HashMap<String, String>();
		index = i;
		values.put("Data Rate", "0");//actualizado quando iniciado
		values.put("Sensitivity", "10");
	}

	@Override
	public String getDescription() {
		return "Change de Senstivity of the slider sensor";
	}

	@Override
	public String getName() {
		return "Senstivity Service";
	}

	@Override
	public HashMap<String, String> getCollection() {
		return values;
	}

	@Override
	public Set<String> getProperties() {
		return values.keySet();
	}

	@Override
	public String getValue(String s) {
		return values.get(s);
	}

	@Override
	public void setValue(String s, String v) {
		changed = 1;
		if(s.equals("Data Rate")){
			try {
				itk.setDataRate(index, (new Integer(v).intValue()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (PhidgetException e) {
				e.printStackTrace();
			}
		}
		else if(s.equals("Sensitivity")){
			try {
				itk.setSensorChangeTrigger(index, (new Integer(v).intValue()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (PhidgetException e) {
				e.printStackTrace();
			}
		}
		values.put(s, v);
	}

	@Override
	public int getChanged() {
		int rt =changed;
		changed=0;
		return rt;
	}
	
	@Override
	public void setChanged(int changed) {
		this.changed = changed;
	}
	

}
