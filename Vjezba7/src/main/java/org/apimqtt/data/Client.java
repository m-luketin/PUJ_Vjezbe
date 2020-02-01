package org.apimqtt.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String email;
    public String password;

    public Client() {}
    public Client(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy="client")
    private List<ClientTopic> clientTopics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() { return this.password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ClientTopic> getClientTopics() {
        return clientTopics;
    }

    public void setClientTopics(List<ClientTopic> clientTopics) {
        this.clientTopics = clientTopics;
    }
}
