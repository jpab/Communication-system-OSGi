package org.temperature.impls;

import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;

public class ErrorListenerDevice implements ErrorListener {

	@Override
	public void error(ErrorEvent ee) {
		System.out.println("ErrorListener of Device:"+ee.toString());
	}

}
