package Vjezba5_PUJ.classes;

import Vjezba5_PUJ.Subscriber;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WaterStatisticFrame extends JFrame
{
    public ArrayList<WaterDisplayStatistic> WaterDisplayStatisticArrayList;
    public JPanel panel;

    public <WaterDisplayStatistics> WaterStatisticFrame(Configuration configuration) throws MqttException
    {
        WaterDisplayStatisticArrayList = new ArrayList<WaterDisplayStatistic>();
        for (WaterDisplayDto display : configuration.displays)
            WaterDisplayStatisticArrayList.add(new WaterDisplayStatistic(display.unitOfMeasurement));

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.setVisible(true);
        WaterDisplayStatisticArrayList.forEach(waterDisplayStatistic -> waterDisplayStatistic.getLabelArray().forEach(panel::add));

        SensorCallback callback = new SensorCallback(WaterDisplayStatisticArrayList);

        Subscriber.createClient(configuration.broker, configuration.clientId, configuration.topic, callback);

        super.add(panel);
        super.setSize(600, 500);
        super.setVisible(true);
    }
}
