package wabnd.datahandler.api;

import org.osgi.framework.ServiceRegistration;



public interface DataHandlerService {
	/*Key is the name of the value*/
	void setValue(String s, String v, ServiceRegistration serviceRegistration);
}