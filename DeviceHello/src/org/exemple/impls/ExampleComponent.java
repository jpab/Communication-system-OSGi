package org.exemple.impls;

import org.deviceservice.api.DeviceService;

public class ExampleComponent implements DeviceService{
	@Override
	public String sayHello(String name) {
		return "Hello "+name;
	}
	

	// TODO: class provided by template

}