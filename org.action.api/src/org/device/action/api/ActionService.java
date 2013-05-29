package org.device.action.api;


import org.deviceservice.api.DeviceService;
import org.osgi.framework.ServiceReference;

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
public interface ActionService extends DeviceService{
	public void deployService();
	public ServiceReference[] getActuators();
	public void setActuator(ServiceReference dc);
	public ServiceReference getActuator();
	public Class<?> getC();
}