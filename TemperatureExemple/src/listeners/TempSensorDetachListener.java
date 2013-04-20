/* - TempSensorDetachListener -
 * Clear the fields and disable the modify sensitivity textbox so that
 * new sensity value can't be sent while there is no device attached, 
 * otherwise this would generate a PhidgetException
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package listeners;

import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.DetachListener;
import com.phidgets.event.DetachEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TempSensorDetachListener implements DetachListener{
    
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
    
    /** Creates a new instance of TempSensorDetachListener */
    public TempSensorDetachListener(JFrame appFrame, JTextField attachedTxt,
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
    
    public void detached(DetachEvent de)
    {
        try
        {
            TemperatureSensorPhidget detached = (TemperatureSensorPhidget)de.getSource();
            attachedTxt.setText(Boolean.toString(detached.isAttached()));
            nameTxt.setText("");
            serialTxt.setText("");
            versionTxt.setText("");
            numTCTxt.setText("");
            potTxt.setText("");
            tcTempTxt.setText("");
            onBoardTxt.setText("");
            sensitivityTxt.setText("");
            sensitivityScrl.setEnabled(false);
            sensitivityScrl.setValue(0);
            potentialRngLbl.setText("()");
            thermoRngLbl.setText("()");
            ambientRngLbl.setText("()");
            thermoTypeCmb.setEnabled(false);
        }
        catch(PhidgetException ex)
        {
            JOptionPane.showMessageDialog(appFrame, ex.getDescription(), "Phidget error " + ex.getErrorNumber(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
