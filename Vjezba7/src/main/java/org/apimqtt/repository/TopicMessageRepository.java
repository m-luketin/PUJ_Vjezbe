package org.apimqtt.repository;

import org.apimqtt.data.TopicMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMessageRepository extends CrudRepository<TopicMessage, Long> {
}
