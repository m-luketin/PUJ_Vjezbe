package Vjezba5_PUJ.classes;

import javax.swing.*;
import java.util.ArrayList;

public class WaterDisplayStatistic
{
    private JLabel unitOfMeasurement;
    private JLabel data;
    public ArrayList<JLabel> labelArray;

    public WaterDisplayStatistic(String unit)
    {
        unitOfMeasurement = new JLabel("<html><div style='text-align:center;padding:20px 4px;width:200px;background-color:#ccc;font-size:20px;color:#eaefee;margin-left:15px;'>" + unit + "</div></html>");
        data = new JLabel();

        unitOfMeasurement.setSize(100,50);
        data.setSize(600,50);
        labelArray = new ArrayList<JLabel>();
        labelArray.add(unitOfMeasurement);
        labelArray.add(data);
    }

    public boolean HasUnitType(String unitType) {
        return unitOfMeasurement.getText().equals(unitType);
    }

    public ArrayList<JLabel> getLabelArray() {
        return labelArray;
    }

    public void updateIfCorrectType(Sensor sensor)
    {
        if(unitOfMeasurement.getText().contains(sensor.UnitOfMeasurement))
        {
            data.setText("<html><div style='text-align:center;display:block;width:200px;overflow:hidden;background-color:#00baba;font-size: 20px;padding:15px;border-radius:5px;'>" +
                "<div>" + sensor.CurrentValue + "</div></html>" );
        }
    }
}
