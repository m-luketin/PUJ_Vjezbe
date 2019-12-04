package Vjezba4_PUJ.classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;
import java.util.ArrayList;
import java.util.List;

public class Watermeter
{
    private String BrokerAddress;
    private List<Sensor> Sensors;

    public Watermeter() {}

    public void publish()
    {
        Publisher publisher = new Publisher(BrokerAddress);

        while(true)
        {
            for (Sensor sensor : Sensors)
            {
                sensor.RandomizeCurrentValue();
                publisher.publish(sensor.toString(), "topic");
            }
        }
    }

    public static Watermeter createWatermeter(String path) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, Watermeter.class);
    }

    public void setSensors(List<Sensor> sensors)
    {
        Sensors = sensors;
    }

    public void setBrokerAddress(String brokerAddress)
    {
        BrokerAddress = brokerAddress;
    }
}

