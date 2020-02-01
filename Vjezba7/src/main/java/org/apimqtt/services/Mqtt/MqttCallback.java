package org.apimqtt.services.Mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apimqtt.dtos.TopicMessageDto;
import org.apimqtt.services.Topic.TopicService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MqttCallback implements org.eclipse.paho.client.mqttv3.MqttCallback {
    private TopicService topicService;

    public MqttCallback(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topicName, MqttMessage mqttMessage) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.readValue(mqttMessage.getPayload(), String.class);
        TopicMessageDto topicMessageDto = new TopicMessageDto(topicName, payload);
        this.topicService.postTopicMessage(topicMessageDto, "Local");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
