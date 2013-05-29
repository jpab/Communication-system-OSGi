package org.advancedServo.services;

import java.util.HashMap;
import java.util.Set;

import org.device.switchbtn.api.SwitchService;

import com.phidgets.AdvancedServoPhidget;
import com.phidgets.PhidgetException;

public class OpenDoorService implements SwitchService {

	AdvancedServoPhidget asp = null;
	HashMap<String, Boolean> values= null;
	private int changed = 1;
	
	public OpenDoorService(AdvancedServoPhidget asp) {
		this.asp = asp;
	}

	@Override
	public String getDescription() {
		return "Service capable of open one door";
	}

	@Override
	public String getName() {
		return "OpenDoor";
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
		changed = 1; 
		values.put(s, b);
	}

	@Override
	public int getChanged() {
		if(changed==0){
			changed =1;
			return 0;
		}
		return 1;
	}

	@Override
	public void setChanged(int changed) {
		if(changed == 1){
			try {
				asp.setEngaged(0, false);
				asp.setPosition(0, 100);
				asp.setEngaged(0, true);
				this.changed=1;
				this.wait(15000);	
				asp.setEngaged(0, false);
				asp.setPosition(0, 0);
				asp.setEngaged(0, true);
				this.wait(1000);
				this.changed=1;
				asp.setEngaged(0, false);
			} catch (PhidgetException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
}