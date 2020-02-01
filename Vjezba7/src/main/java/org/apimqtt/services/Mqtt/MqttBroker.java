package org.apimqtt.services.Mqtt;

import org.apimqtt.services.Topic.TopicService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import java.net.ConnectException;

@Component
public class MqttBroker {
    public final static String brokerUrl = "tcp://localhost:1883";
    private static MqttClient mqttClient;

    public MqttBroker(TopicService topicService) throws MqttException {
        if(mqttClient == null) {
            try {

            mqttClient = new MqttClient(brokerUrl, "ApiMqtt");
            mqttClient.connect();
            mqttClient.subscribe("#");
            mqttClient.setCallback(new MqttCallback(topicService));
            }
            catch (Exception e) {

            }
        }
    }
}
