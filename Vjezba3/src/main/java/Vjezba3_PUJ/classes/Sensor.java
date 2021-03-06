package Vjezba3_PUJ.classes;

public class Sensor {
    private String Description;
    private String DataType;
    private Integer Factor;
    private Integer LowerLimit;
    private Integer UpperLimit;
    private String UnitOfMeasurement;
    private Double CurrentValue = 0.0;

    public Sensor(String description, String dataType, Integer factor, Integer lowerLimit, Integer upperLimit, String unitOfMeasurement)
    {
        Description = description;
        DataType = dataType;
        Factor = factor;
        LowerLimit = lowerLimit;
        UpperLimit = upperLimit;
        UnitOfMeasurement = unitOfMeasurement;
    }

    public void RandomizeCurrentValue()
    {
        CurrentValue = (Math.random() * (((Double.valueOf(UpperLimit) / Factor) - (Double.valueOf(LowerLimit) / Factor)) + 1));
    }

    @Override
    public String toString()
    {
        return "{ \"description\": \"" + Description +
                "\", \"dataType\": \"" + DataType +
                "\", \"factor\": \"" + Factor +
                "\", \"lowerLimit\": \"" + LowerLimit +
                "\", \"upperLimit\": \"" + UpperLimit +
                "\", \"unitOfMeasurement\": \"" + UnitOfMeasurement +
                "\", \"currentValue\": \"" + CurrentValue + "\" }";
    }
}
