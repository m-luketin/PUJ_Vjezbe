package Vjezba3_PUJ.classes;

import java.util.Arrays;
import java.util.List;

public class Watermeter {
    public List<Sensor> Sensors;

    public Watermeter(Publisher publisher)
    {
        Sensors = Arrays.asList(new Sensor("Trenutna temperatura vode", "int16", 10, -32668, 32668, "Celsius", publisher),
                new Sensor("Trenutni tlak vode", "uint16", 1000, 0, 65336, "Bar", publisher),
                new Sensor("Potrosnja u zadnjih 1 min, 10 min, 1 sat, 1 dan", "uint16", 1, 0, 65336, "Liter", publisher),
                new Sensor("Potrosnja u zadnjih 1 tjedan, 1 mjesec, 1 godinu", "uint16", 10, 0, 65336, "CubicMeter", publisher));
    }

    public void separateThreads() {
        for(Sensor sensor : Sensors) {
            Thread newThread = new Thread(sensor);
            newThread.start();
        }
    }
}
