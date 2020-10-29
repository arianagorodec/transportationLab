package com.gorodeckaya.service.impl;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.repository.ClientRepository;
import com.gorodeckaya.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(long id) {
        clientRepository.deleteById(id);
    }


    @Override
    public Client getByEmail(String email) {
        return clientRepository.findByEmail(email);
    }


    @Override
    public Client editClient(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getInfoClient() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client client = getByEmail(auth.getName());
        return client;
    }
}
