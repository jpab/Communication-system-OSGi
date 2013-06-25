package org.interface888.impl2;

import com.phidgets.PhidgetException;

public interface PhidgetDevice {
	public void unregist();
	public void regist() throws PhidgetException;
	public void changed(int value);
}
