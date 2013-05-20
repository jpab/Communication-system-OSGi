package org.temperature.impls;



public class TemperatureExample
{
	/*
	public static final void main(String args[]) throws Exception {
		TemperatureSensorPhidget tempsensor;
		Boolean at;

		System.out.println(Phidget.getLibraryVersion());

		tempsensor = new TemperatureSensorPhidget();
		tempsensor.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae) {
				System.out.println("attachment of " + ae);
				((TemperatureSensorPhidget)ae.getSource())
			}
		});
		tempsensor.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
				at=false;
			}
		});
		tempsensor.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		tempsensor.addTemperatureChangeListener(new TemperatureChangeListener()
		{
			public void temperatureChanged(TemperatureChangeEvent oe)
			{
				System.out.println(oe);
			}
		});
		
		
		tempsensor.openAny();
		System.out.println("waiting for TemperatureSensor attachment...");
		tempsensor.waitForAttachment();
		at= true;

		System.out.println("Serial: " + tempsensor.getSerialNumber());
		tempsensor.setTemperatureChangeTrigger(0, 0.1);
		System.out.println("trigger: " + tempsensor.getTemperatureChangeTrigger(0));
		System.out.println("Outputting events.  Input to stop.");
		System.in.read();
		System.out.print("closing...");
		tempsensor.close();
		tempsensor = null;
		System.out.println(" ok");
		if (at) {
			System.out.println("wait for finalization...");
			System.gc();
		}
		
		/*Working code
		* 		InterfaceKitPhidget itk;
		*		System.out.println(Phidget.getLibraryVersion());
		*
		*		itk = new InterfaceKitPhidget();
		*		itk.openAny();
		*		itk.waitForAttachment();
		*		System.out.println( Double.valueOf((itk.getSensorValue(0)-200)/4) );*/
				
		
	
}
