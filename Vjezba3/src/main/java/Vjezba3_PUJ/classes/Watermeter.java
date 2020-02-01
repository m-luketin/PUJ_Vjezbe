package Vjezba3_PUJ.classes;

import java.util.Arrays;
import java.util.List;

public class Watermeter {
    public List<Sensor> Sensors;

    public Watermeter()
    {
        Sensors = Arrays.asList(new Sensor("Trenutna temperatura vode", "int16", 10, -32668, 32668, "Celsius"),
                new Sensor("Trenutni tlak vode", "uint16", 1000, 0, 65336, "Bar"),
                new Sensor("Potrosnja u zadnjih 1 min, 10 min, 1 sat, 1 dan", "uint16", 1, 0, 65336, "Liter"),
                new Sensor("Potrosnja u zadnjih 1 tjedan, 1 mjesec, 1 godinu", "uint16", 10, 0, 65336, "CubicMeter"));
    }

    public void publish()
    {
        Publisher publisher = new Publisher();

        while(true) {
            for (Sensor sensor : Sensors) {
                sensor.RandomizeCurrentValue();
                publisher.publish(sensor.toString(), "topic");
            }
        }
    }
}
