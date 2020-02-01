package org.apimqtt.services.Client;

import org.apimqtt.data.Client;
import org.apimqtt.data.ClientTopic;
import org.apimqtt.data.Topic;
import org.apimqtt.repository.ClientRepository;
import org.apimqtt.repository.ClientTopicRepository;
import org.apimqtt.repository.TopicRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final ClientTopicRepository clientTopicRepository;
    private final TopicRepository topicRepository;

    public ClientService(
            ClientRepository clientRepository,
            ClientTopicRepository clientTopicRepository,
            TopicRepository topicRepository
    ) {
        this.clientRepository = clientRepository;
        this.clientTopicRepository = clientTopicRepository;
        this.topicRepository = topicRepository;
    }

    public void addClient(Client client) {
        if(this.clientRepository.findByEmail(client.email) != null) return;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        client.password = bCryptPasswordEncoder.encode(client.password);
        this.clientRepository.save(client);
    }

    public Client findClientByEmail(String email) {
        return this.clientRepository.findByEmail(email);
    }

    public List<Topic> getAllTopicsByClient(Client client) {
        Client clientDb = this.clientRepository.findByEmail(client.getEmail());
        List<ClientTopic> clientTopics = this.clientTopicRepository.findByClient(clientDb);
        List<Topic> topics = new ArrayList<>(clientTopics.size());
        clientTopics.forEach(clientTopic -> {
            topics.add(clientTopic.getTopic());
        });

        return topics;
    }

    public void addClientToTopic(Client client, String topicName) {
        Topic topic = this.topicRepository.findByTopicName(topicName);
        if(topic == null) return;
        Client clientDb = this.clientRepository.findByEmail(client.getEmail());
        ClientTopic clientTopic = new ClientTopic(clientDb, topic);
        this.clientTopicRepository.save(clientTopic);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Client client = findClientByEmail(s);


        if(client == null){
            throw new UsernameNotFoundException(s);
        }

        return new User(client.getEmail(), client.getPassword(), Collections.emptyList());
    }
}
