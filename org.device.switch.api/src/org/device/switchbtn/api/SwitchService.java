package org.device.switchbtn.api;

import java.util.HashMap;
import java.util.Set;

import org.deviceservice.api.DeviceService;

import aQute.bnd.annotation.ProviderType;

/**
 * <p>
 * This is an example of an interface that is expected to be implemented by Providers of the API. Adding methods to this
 * interface is a minor change, because only Providers will be affected.
 * </p>
 * This service its a switch type. Provide one offers the possibility to a consumer change it. Can have more than one.
 * @see ProviderType
 * @since 1.0
 */
@ProviderType
public interface SwitchService extends DeviceService{

	HashMap<String,Boolean> getCollection();
	Set<String> getProperties();
	Boolean getState(String s);
	void setState(String s, Boolean b);
	int getChanged();
	void setChanged(int changed);

}