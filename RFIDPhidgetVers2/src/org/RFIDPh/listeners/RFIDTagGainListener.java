package org.RFIDPh.listeners;

import org.RFIDPh.impl.RFIDPh;

import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagGainListener;

public class RFIDTagGainListener implements TagGainListener {	
	RFIDPh rfid = null;
	public RFIDTagGainListener(RFIDPh rfid){
		this.rfid = rfid;
	}
	
	@Override
	public void tagGained(TagGainEvent tge) {
		rfid.deployObject();
	}

}
