package Vjezba3_PUJ.classes;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher
{
    private static String BROKER = "tcp://localhost:1883";
    private MqttClient mqttClient;

    public Publisher()
    {
        String client = "Matija1213S";
        try
        {
            mqttClient = new MqttClient(BROKER, client);
        }
        catch (MqttException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void publish(String data, String topic) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(data.getBytes());
        try {
            this.mqttClient.connect();
            this.mqttClient.publish(topic, mqttMessage);
            this.mqttClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
