package Vjezba5_PUJ;

import Vjezba5_PUJ.classes.WaterStatisticFrame;
import com.fasterxml.jackson.databind.ObjectMapper;
import Vjezba5_PUJ.classes.Configuration;
import org.eclipse.paho.client.mqttv3.MqttException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public final class App
{
    private App() {}

    public static void main(String[] args) throws IOException, MqttException
    {
        String filePath = "C:\\Users\\mluketin\\Desktop\\PUJ_Vjezbe\\Vjezba5\\src\\main\\java\\Vjezba5_PUJ\\settings\\uiconfiguration.json";
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = mapper.readValue(file, Configuration.class);

        SwingUtilities.invokeLater(() -> {
            try
            {
                new WaterStatisticFrame(configuration);
            }
            catch (MqttException e)
            {
                e.printStackTrace();
            }
        });
    }
}
