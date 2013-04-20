package org.example;

import org.deviceservice.api.DeviceService;

public class ServicesThread extends Thread {

	private volatile boolean active = true;
	private final DeviceService service;

	public ServicesThread(DeviceService service) {
		this.service = service;
	}
	
	public void run() {
		while (active) {
			System.out.println(service.sayHello("Jo�o Brand�o"));
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println("Thread interrupted " + e.getMessage());
			}
		}
	}
	public void stopThread() {
		active = false;
	}
}

