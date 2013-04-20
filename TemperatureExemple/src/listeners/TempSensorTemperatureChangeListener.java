/* - TempSensorTemperatureChangeListener -
 * We'll display the current temperature reading from the sensor to the
 * specified textbox as they come in
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package listeners;

import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.TemperatureChangeListener;
import com.phidgets.event.TemperatureChangeEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TempSensorTemperatureChangeListener implements TemperatureChangeListener{
    
    private JFrame appFrame;
    private JTextField potTxt;
    private JTextField tcTempTxt;
    private JTextField onBoardTxt;
    
    /** Creates a new instance of TempSensorTemperatureChangeListener */
    public TempSensorTemperatureChangeListener(JFrame appFrame, JTextField potTxt,
            JTextField tcTempTxt, JTextField onBoardTxt)
    {
        this.appFrame = appFrame;
        this.potTxt = potTxt;
        this.tcTempTxt = tcTempTxt;
        this.onBoardTxt = onBoardTxt;
    }

    public void temperatureChanged(TemperatureChangeEvent temperatureChangeEvent)
    {
        try
        {
            TemperatureSensorPhidget source = (TemperatureSensorPhidget)temperatureChangeEvent.getSource();


            if(temperatureChangeEvent.getIndex() == 0)
            {
                onBoardTxt.setText(Double.toString(source.getAmbientTemperature()));
                tcTempTxt.setText(Double.toString(source.getTemperature(temperatureChangeEvent.getIndex())));
                potTxt.setText(Double.toString(source.getPotential(temperatureChangeEvent.getIndex())));
            }
        }
        catch (PhidgetException ex)
        {
            JOptionPane.showMessageDialog(appFrame, ex.getDescription(), "Phidget error " + ex.getErrorNumber(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
