package org.advancedServo.listners;

import org.advancedServo.impl.AdvancedServoPh;

import com.phidgets.event.ServoPositionChangeEvent;
import com.phidgets.event.ServoPositionChangeListener;



public class DoorMovedListner implements ServoPositionChangeListener {	
	AdvancedServoPh asp = null;
	public DoorMovedListner(AdvancedServoPh asp){
		this.asp = asp;
	}
	
	@Override
	public void servoPositionChanged(ServoPositionChangeEvent arg0) {
		asp.deployService();
	}

}
