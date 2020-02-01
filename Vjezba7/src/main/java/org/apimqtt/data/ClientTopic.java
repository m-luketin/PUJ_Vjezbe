package org.apimqtt.data;

import javax.persistence.*;

@Entity
@Table()
public class ClientTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable = false)
    private Topic topic;

    public ClientTopic() {}

    public ClientTopic(Client client, Topic topic) {
        this.client = client;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
