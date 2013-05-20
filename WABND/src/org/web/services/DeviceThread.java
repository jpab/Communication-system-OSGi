package org.web.services;

import org.deviceservice.api.DeviceService;
import org.deviceservice.sensing.api.DeviceSensing;

public class DeviceThread extends Thread {

    private volatile boolean active = true;
    private final DeviceSensing service;

    public DeviceThread(DeviceService service) {
      this.service = (DeviceSensing) service;
    }

    public void run() {
      while (active) {
        System.out.println(service.getName() + "is active!!!! \n" + service.getValue("TemperatureC"));
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