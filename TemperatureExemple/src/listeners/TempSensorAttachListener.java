/* - TempSensorAttachListener -
 * We'll populate the fields in the GUI and enable the modify sensitivity scroll
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package listeners;

import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachListener;
import com.phidgets.event.AttachEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TempSensorAttachListener implements AttachListener{
    
    private JFrame appFrame;
    private JTextField attachedTxt;
    private JTextArea nameTxt;
    private JTextField serialTxt;
    private JTextField versionTxt;
    private JTextField numTCTxt;
    private JSlider sensitivityScrl;
    private JTextField sensitivityTxt;
    private JTextField potTxt;
    private JLabel potentialRngLbl;
    private JTextField tcTempTxt;
    private JLabel thermoRngLbl;
    private JTextField onBoardTxt;
    private JLabel ambientRngLbl;
    private JComboBox thermoTypeCmb;
    
    /** Creates a new instance of TempSensorAttachListener */
    public TempSensorAttachListener(JFrame appFrame, JTextField attachedTxt,
            JTextArea nameTxt, JTextField serialTxt, JTextField versionTxt,
            JTextField numTCTxt, JSlider sensitivityScrl, JTextField potTxt,
            JLabel potentialRngLbl, JTextField tcTempTxt, JLabel thermoRngLbl,
            JTextField sensitivityTxt, JTextField onBoardTxt, JLabel ambientRngLbl,
            JComboBox thermoTypeCmb)
    {
        this.appFrame = appFrame;
        this.attachedTxt = attachedTxt;
        this.nameTxt = nameTxt;
        this.serialTxt = serialTxt;
        this.versionTxt = versionTxt;
        this.numTCTxt = numTCTxt;
        this.sensitivityScrl = sensitivityScrl;
        this.sensitivityTxt = sensitivityTxt;
        this.potTxt = potTxt;
        this.potentialRngLbl = potentialRngLbl;
        this.tcTempTxt = tcTempTxt;
        this.thermoRngLbl = thermoRngLbl;
        this.onBoardTxt = onBoardTxt;
        this.ambientRngLbl = ambientRngLbl;
        this.thermoTypeCmb = thermoTypeCmb;
    }

    public void attached(AttachEvent ae)
    {
        try
        {
            TemperatureSensorPhidget attached = (TemperatureSensorPhidget)ae.getSource();
            attachedTxt.setText(Boolean.toString(attached.isAttached()));
            nameTxt.setText(attached.getDeviceName());
            serialTxt.setText(Integer.toString(attached.getSerialNumber()));
            versionTxt.setText(Integer.toString(attached.getDeviceVersion()));
            numTCTxt.setText(Integer.toString(attached.getSensorCount()));
            onBoardTxt.setText(Double.toString(attached.getAmbientTemperature()));
            sensitivityScrl.setEnabled(true);
            sensitivityScrl.setValue(((int)attached.getTemperatureChangeTrigger(0))*100);
            sensitivityTxt.setText(Integer.toString(sensitivityScrl.getValue()));
            
            thermoRngLbl.setText("(" + attached.getTemperatureMin(0) + "°C - " + attached.getTemperatureMax(0) + "°C)");
            
            try
            {
                potentialRngLbl.setText("(" + attached.getPotentialMin(0) + "mV - " + attached.getPotentialMax(0) + "mV)");
            }
            catch(PhidgetException ex)
            {
                potentialRngLbl.setText("(Not Supported)");
            }
            
            ambientRngLbl.setText("(" + attached.getAmbientTemperatureMin() + "°C - " + attached.getAmbientTemperatureMax() + "°C)");
            
            thermoTypeCmb.setEnabled(true);
            
            switch(attached.getThermocoupleType(0))
            {
                case TemperatureSensorPhidget.PHIDGET_TEMPERATURE_SENSOR_K_TYPE:
                    thermoTypeCmb.setSelectedItem("K-Type");
                    break;
                case TemperatureSensorPhidget.PHIDGET_TEMPERATURE_SENSOR_J_TYPE:
                    thermoTypeCmb.setSelectedItem("J-Type");
                    break;
                case TemperatureSensorPhidget.PHIDGET_TEMPERATURE_SENSOR_E_TYPE:
                    thermoTypeCmb.setSelectedItem("E-Type");
                    break;
                case TemperatureSensorPhidget.PHIDGET_TEMPERATURE_SENSOR_T_TYPE:
                    thermoTypeCmb.setSelectedItem("T-Type");
                    break;
            }
        }
        catch (PhidgetException ex)
        {
            JOptionPane.showMessageDialog(appFrame, ex.getDescription(), "Phidget error " + ex.getErrorNumber(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
