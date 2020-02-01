package org.apimqtt.repository;

import org.apimqtt.data.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface TopicRepository extends CrudRepository<Topic, Long> {
    Topic findByTopicName(String topicName);
    List<Topic> findAll();
}
