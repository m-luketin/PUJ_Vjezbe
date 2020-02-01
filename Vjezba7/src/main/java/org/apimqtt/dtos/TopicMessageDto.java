package org.apimqtt.dtos;

import com.fasterxml.jackson.annotation.JsonView;

@JsonView(TopicMessageDto.class)
public class TopicMessageDto {
    public String Topic;
    public String Message;

    public TopicMessageDto() {}

    public TopicMessageDto(String topic, String message) {
        this.Topic = topic;
        this.Message = message;
    }
}
