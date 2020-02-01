package org.apimqtt.data;

import javax.persistence.*;

@Entity
public class TopicMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String message;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable = false)
    private Topic topicOnMessage;

    public TopicMessage(){}

    public TopicMessage(String message, Topic topic) {
        this.message = message;
        this.topicOnMessage = topic;
    }

    public TopicMessage(byte[] message) {
        this.message = message.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic getTopicOnMessage() {
        return topicOnMessage;
    }

    public void setTopicOnMessage(Topic topicOnMessage) {
        this.topicOnMessage = topicOnMessage;
    }
}
