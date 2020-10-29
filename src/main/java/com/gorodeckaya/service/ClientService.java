package com.gorodeckaya.service;

import com.gorodeckaya.entity.Client;
import java.util.List;

public interface ClientService {
    Client addClient(Client client);
    void deleteById(long id);
    Client editClient(Client client);
    List<Client> getAll();
    public Client getByEmail(String email);
}
