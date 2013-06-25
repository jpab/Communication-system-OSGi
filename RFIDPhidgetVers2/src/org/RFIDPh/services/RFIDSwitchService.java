package org.RFIDPh.services;

import java.util.HashMap;
import java.util.Set;

import org.device.switchbtn.api.SwitchService;

import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;

public class RFIDSwitchService implements SwitchService{

	RFIDPhidget rph =null;
	HashMap<String, Boolean> values= null;
	int changed = 1;
	
	public RFIDSwitchService(RFIDPhidget rph) {
		values = new HashMap<String, Boolean>();
		this.rph =rph;
		try {
			values.put("Led", (new Boolean(this.rph.getLEDOn())));
			values.put("Antenna", (new Boolean(this.rph.getAntennaOn())));
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getDescription() {
		
		return "Switchs to the Led an Antenna of RFID ";
	}

	@Override
	public String getName() {
		return "Switchs";
	}

	@Override
	public HashMap<String, Boolean> getCollection() {
		
		return values;
	}

	@Override
	public Set<String> getProperties() {
		return values.keySet();
	}

	@Override
	public Boolean getState(String s) {
		return values.get(s);
	}

	@Override
	public void setState(String s, Boolean b) {
		values.put(s,b);
		try {
			if(s.equals("Led")){
			rph.setLEDOn(b.booleanValue());
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! RFIDLL"+b.booleanValue());
		} else if(s.equals("Antenna")){
			rph.setAntennaOn(b.booleanValue());
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! RFIDAA"+b.booleanValue());
		}
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int getChanged() {
		return changed;
	}

	@Override
	public void setChanged(int changed) {
		this.changed =changed;
		
	}

}
