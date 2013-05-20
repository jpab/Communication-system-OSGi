package org.interface888.listener;

import org.interface888.impl.Interface888;
import org.interface888.impl.RotationSensor;
import org.interface888.impl.TemperatureSensor;
import org.osgi.framework.BundleContext;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;

public class Interface888Change implements SensorChangeListener{
	BundleContext bc =null;
	
	public Interface888Change(BundleContext b) {
		bc = b;
	}

	@Override
	public void sensorChanged(SensorChangeEvent sce) {
		try {
			//System.out.println("Phidget: "+sce.getSource().toString()+"Value"+sce.getValue()+" \n Serial"+sce.getSource().getSerialNumber()+"Index "+sce.getIndex());
			//Phidget p = sce.getSource();
			Integer index = new Integer(sce.getIndex());
			//System.out.println("OUT"+((InterfaceKitPhidget)sce.getSource()).getOutputState(index.intValue()) + " IN "+ ((InterfaceKitPhidget)sce.getSource()).getInputState(index.intValue()));
			switch(index.intValue()){
				
			case 0 : 
				touchSensor(index,sce);
				break;
			case 1 :
				temperatureSensor(index,sce);
				break;
			case 2 : 
				precisionLightSensor(index,sce);
				break;
			case 3 : 
				presenceSensor(index,sce);
				break;
			case 4 :
				sliderSensor(index,sce);
				break;
			case 5 : 
				precisionLightSensor(index,sce);
				break;
			case 6 : 
				rotationSensor(index,sce);
				break;
			default :  throw new PhidgetException(index, "Index not found in the swich options");
			}
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
	}
	
	/*Number 6
	 * 
	 * */
	private synchronized void rotationSensor(Integer index, SensorChangeEvent sce) {
		if (Interface888.phidgets.containsKey(index)){ // if it was attached
			if(sce.getValue() < 5){ //if is desattached
				//System.out.println("it was attached but now has been desattached");
				Interface888.phidgets.get(index).unregist();
				Interface888.phidgets.remove(index);
			}
			else{ //if temperature change
				//System.out.println("it was attached now just change");
				Interface888.phidgets.get(index).changed(sce.getValue());
				//System.out.println("Contains1:"+Interface888.phidgets.containsKey(index));
				}
		}
		else{ //if it was desatached
			//System.out.println("it was desatached");
			RotationSensor rt = new RotationSensor((InterfaceKitPhidget)sce.getSource(), bc);
			Interface888.phidgets.put(new Integer(index.intValue()), rt);
			rt.regist();
		}
		
	}

	private synchronized void sliderSensor(Integer index, SensorChangeEvent sce) {
		// TODO Auto-generated method stub
		
	}

	private synchronized void presenceSensor(Integer index, SensorChangeEvent sce) {
		// TODO Auto-generated method stub
		
	}

	private synchronized void precisionLightSensor(Integer index, SensorChangeEvent sce) {
		// TODO Auto-generated method stub
		
	}

	private synchronized void temperatureSensor(Integer index, SensorChangeEvent sce) {
		//System.out.println("Contains2:"+Interface888.phidgets.containsKey(index));
		if (Interface888.phidgets.containsKey(index)){ // if it was attached
			if(sce.getValue() < 10){ //if is desattached
				//System.out.println("it was attached but now has been desattached");
				Interface888.phidgets.get(index).unregist();
				Interface888.phidgets.remove(index);
			}
			else{ //if temperature change
				//System.out.println("it was attached now just change");
				Interface888.phidgets.get(index).changed(sce.getValue());
				//System.out.println("Contains1:"+Interface888.phidgets.containsKey(index));
				}
		}
		else{ //if it was desatached
			//System.out.println("it was desatached");
			TemperatureSensor tmp = new TemperatureSensor((InterfaceKitPhidget)sce.getSource(), bc);
			Interface888.phidgets.put(new Integer(index.intValue()), tmp);
			tmp.regist();
		}	
		
	}

	private synchronized void touchSensor(Integer index, SensorChangeEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
