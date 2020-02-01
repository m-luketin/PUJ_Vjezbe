package org.apimqtt.repository;

import org.apimqtt.data.Client;
import org.apimqtt.data.ClientTopic;
import org.apimqtt.data.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface ClientTopicRepository extends CrudRepository<ClientTopic, Long> {
    List<ClientTopic> findByClient
            (Client client);
    List<ClientTopic> findByTopic(Topic topic);
}
