package org.apimqtt.services.Topic;

import org.apimqtt.data.Topic;
import org.apimqtt.data.TopicMessage;
import org.apimqtt.dtos.TopicMessageDto;
import org.apimqtt.repository.TopicMessageRepository;
import org.apimqtt.repository.TopicRepository;
import org.apimqtt.services.Mqtt.MqttBroker;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMessageRepository topicMessageRepository;

    public TopicService(
            TopicRepository topicRepository,
            TopicMessageRepository topicMessageRepository
    ) {
        this.topicRepository = topicRepository;
        this.topicMessageRepository = topicMessageRepository;
    }

    public void postTopicMessage(TopicMessageDto topicMessageDto, String email) throws MqttException {
        this.postToTopic(topicMessageDto, email);
        Topic topic = new Topic(topicMessageDto.Topic);
        Topic topicDb = this.topicRepository.findByTopicName(topic.getTopicName());
        if(topicDb == null) {
            topicDb = new Topic(topic.getTopicName());
            this.topicRepository.save(topicDb);
        }
        TopicMessage topicMessage = new TopicMessage(topicMessageDto.Message, topicDb);
        this.topicMessageRepository.save(topicMessage);
    }

    public void postToTopic(TopicMessageDto topicMessageDto, String email) throws MqttException {
        MqttClient mqttClient = new MqttClient(MqttBroker.brokerUrl, email);
        mqttClient.connect();
        MqttMessage mqttMessage = new MqttMessage(topicMessageDto.Message.getBytes());
        mqttClient.publish(topicMessageDto.Topic, mqttMessage);
        mqttClient.disconnect();
    }

    public List<String> getAllTopics() {
        List<Topic> topics = this.topicRepository.findAll();
        List<String> topicNames = new ArrayList<>();
        topics.forEach(topic -> {
            topicNames.add(topic.getTopicName());
        });
        return topicNames;
    }
}
