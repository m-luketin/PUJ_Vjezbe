package org.apimqtt.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topicName;

    @OneToMany(mappedBy="topic")
    private List<ClientTopic> topicClients;

    @OneToMany(mappedBy="topicOnMessage")
    private List<TopicMessage> topicMessages;

    public Topic(){}

    public Topic(String topicName) {
        this.topicName = topicName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<ClientTopic> getTopicClients() {
        return topicClients;
    }

    public void setTopicClients(List<ClientTopic> topicClients) {
        this.topicClients = topicClients;
    }

    public List<TopicMessage> getTopicMessages() {
        return this.topicMessages;
    }
}
