package org.apimqtt.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apimqtt.data.Client;
import org.apimqtt.data.Topic;
import org.apimqtt.dtos.TopicMessageDto;
import org.apimqtt.services.Client.ClientService;
import org.apimqtt.services.Topic.TopicService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
    private final TopicService topicService;
    private final ClientService clientService;
    private final ObjectMapper objectMapper;

    public TopicController(
            TopicService topicService,
            ClientService clientService
    ) {
        this.topicService = topicService;
        this.clientService = clientService;
        this.objectMapper = new ObjectMapper();
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<?> Post(@RequestBody String json) throws MqttException, IOException {
        TopicMessageDto topicMessage = this.objectMapper.readValue(json, TopicMessageDto.class);
        String clientEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        this.topicService.postTopicMessage(topicMessage, clientEmail);
        return ResponseEntity.ok("OK");
    }

    @RequestMapping(value = "/getByClient", method = RequestMethod.POST)
    public ResponseEntity<?> GetByClient(@RequestBody String json) throws IOException {
        Client client = this.objectMapper.readValue(json, Client.class);
        List<Topic> topics = this.clientService.getAllTopicsByClient(client);
        List<TopicMessageDto> topicMessageDtos = this.MapTopicMessageDtoByTopicList(topics);
        return ResponseEntity.ok(topicMessageDtos);
    }

    @RequestMapping(value = "/getMine", method = RequestMethod.GET)
    public ResponseEntity<?> GetMine() {
        String clientEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = new Client(clientEmail);
        List<Topic> topics = this.clientService.getAllTopicsByClient(client);
        List<TopicMessageDto> topicMessageDtos = this.MapTopicMessageDtoByTopicList(topics);
        return ResponseEntity.ok(topicMessageDtos);
    }

    @RequestMapping(value = "/addToTopicByTopicName", method = RequestMethod.GET)
    public ResponseEntity<?> AddToTopicByTopicName(@RequestParam String topicName) {
        String clientEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = new Client(clientEmail);
        this.clientService.addClientToTopic(client, topicName);
        return ResponseEntity.ok("Ok");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<?> Get() {
        return ResponseEntity.ok(this.topicService.getAllTopics());
    }

    private List<TopicMessageDto> MapTopicMessageDtoByTopicList(List<Topic> topics) {
        List<TopicMessageDto> topicMessageDtos = new ArrayList<>();
        topics.forEach(topic -> {
            topic.getTopicMessages().forEach(topicMessage -> {
                topicMessageDtos.add(new TopicMessageDto(topic.getTopicName(), topicMessage.getMessage()));
            });
        });
        return topicMessageDtos;
    }
}
