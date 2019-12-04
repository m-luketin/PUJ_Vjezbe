package Vjezba4_PUJ.classes;

import org.json.JSONObject;

public class Sensor {
    private String Description;
    private String DataType;
    private Integer Factor;
    private Integer LowerLimit;
    private Integer UpperLimit;
    private String UnitOfMeasurement;
    private Double CurrentValue = 0.0;

    public Sensor(){}

    public Sensor(String description, String dataType, Integer factor, Integer lowerLimit, Integer upperLimit, String unitOfMeasurement)
    {
        Description = description;
        DataType = dataType;
        Factor = factor;
        LowerLimit = lowerLimit;
        UpperLimit = upperLimit;
        UnitOfMeasurement = unitOfMeasurement;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public void setDataType(String dataType)
    {
        DataType = dataType;
    }

    public void setFactor(Integer factor)
    {
        Factor = factor;
    }

    public void setLowerLimit(Integer lowerLimit)
    {
        LowerLimit = lowerLimit;
    }

    public void setUpperLimit(Integer upperLimit)
    {
        UpperLimit = upperLimit;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement)
    {
        UnitOfMeasurement = unitOfMeasurement;
    }

    public void RandomizeCurrentValue()
    {
        CurrentValue = (Math.random() * (((Double.valueOf(UpperLimit) / Factor) - (Double.valueOf(LowerLimit) / Factor)) + 1));
    }

    @Override
    public String toString()
    {
        return "\n" + Description +
                ":\nTip podatka: " + DataType +
                "\nFaktor: " + Factor +
                "\nRaspon: " + Double.valueOf(LowerLimit)/Factor + " do " + Double.valueOf(UpperLimit)/Factor +
                "\nJedinica: " + UnitOfMeasurement +
                "\nTrenutna vrijednost: " + CurrentValue + "\n\n";
    }
}
