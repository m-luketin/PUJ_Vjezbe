package Vjezba3_PUJ.classes;

public class Sensor implements Runnable{
    private String Description;
    private String DataType;
    private Integer Factor;
    private Integer LowerLimit;
    private Integer UpperLimit;
    private String UnitOfMeasurement;
    private Double CurrentValue = 0.0;
    private Publisher Publisher;

    public Sensor(String description, String dataType, Integer factor, Integer lowerLimit, Integer upperLimit, String unitOfMeasurement, Publisher publisher)
    {
        Description = description;
        DataType = dataType;
        Factor = factor;
        LowerLimit = lowerLimit;
        UpperLimit = upperLimit;
        UnitOfMeasurement = unitOfMeasurement;
        Publisher = publisher;
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

    @Override
    public void run() {
        while(true){
            this.RandomizeCurrentValue();
            this.Publisher.publish(this.toString(), "topic");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
