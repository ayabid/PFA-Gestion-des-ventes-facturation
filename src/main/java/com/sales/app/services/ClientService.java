package com.sales.app.services;

import com.sales.app.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    List<Client> findAllClients();

    List<Client> searchClients(String keyword);

    Client findClientById(Long id);

    void createClient(Client client);

    void updateClient(Client client);

    void deleteClient(Long id);
    public Page<Client> findPaginated(Pageable pageable);
}
