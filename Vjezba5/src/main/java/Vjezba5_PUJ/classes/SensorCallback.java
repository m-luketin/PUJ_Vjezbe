package Vjezba5_PUJ.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

public class SensorCallback implements MqttCallback {
    public ArrayList<Vjezba5_PUJ.classes.WaterDisplayStatistic> waterDisplayStatisticArrayList;
    public String Token;

    public SensorCallback(ArrayList<WaterDisplayStatistic> waterDisplayStatisticArrayList) {
        this.waterDisplayStatisticArrayList = waterDisplayStatisticArrayList;
        System.out.println("ctor callbacka");
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String messageString = mqttMessage.toString();
        Sensor receivedSensor = mapper.readValue(messageString, Sensor.class);
        for (WaterDisplayStatistic waterDisplayStatistic : waterDisplayStatisticArrayList) {
            waterDisplayStatistic.updateIfCorrectType(receivedSensor);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
