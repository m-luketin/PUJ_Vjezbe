package Vjezba5_PUJ.classes;

public class Sensor
{
    public String Description;
    public String DataType;
    public String Factor;
    public String LowerLimit;
    public String UpperLimit;
    public String UnitOfMeasurement;
    public String CurrentValue;

    public Sensor(){}

    public Sensor(String description, String dataType, String factor, String lowerLimit, String upperLimit, String unitOfMeasurement, String currentValue)
    {
        Description = description;
        DataType = dataType;
        Factor = factor;
        LowerLimit = lowerLimit;
        UpperLimit = upperLimit;
        UnitOfMeasurement = unitOfMeasurement;
        CurrentValue = currentValue;
    }

    public void setCurrentValue(String currentValue) {
        CurrentValue = currentValue;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setFactor(String factor) {
        Factor = factor;
    }

    public void setLowerLimit(String lowerLimit) {
        LowerLimit = lowerLimit;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        UnitOfMeasurement = unitOfMeasurement;
    }

    public void setUpperLimit(String upperLimit) {
        UpperLimit = upperLimit;
    }
}
