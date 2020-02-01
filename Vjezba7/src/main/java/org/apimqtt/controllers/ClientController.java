package org.apimqtt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apimqtt.data.Client;
import org.apimqtt.services.Client.ClientService;
import org.apimqtt.services.Client.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public ClientController(
            ClientService clientService,
            JWTService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.clientService = clientService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.objectMapper = new ObjectMapper();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> Register(@RequestBody String json) throws Exception {
        Client client = this.objectMapper.readValue(json, Client.class);
        this.clientService.addClient(client);
        return ResponseEntity.ok("OK!");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> Authenticate(@RequestBody String json) throws Exception{
        Client client = this.objectMapper.readValue(json, Client.class);
        this.authenticate(client);
        Client clientDb = this.clientService.findClientByEmail(client.getEmail());
        String token = this.jwtService.generateToken(clientDb);
        return ResponseEntity.ok(token);
    }

    private void authenticate(Client client) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(client.getEmail(), client.getPassword(), new ArrayList<>()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
