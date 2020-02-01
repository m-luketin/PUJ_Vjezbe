package org.apimqtt.repository;

import org.apimqtt.data.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByEmail(String email);
}
