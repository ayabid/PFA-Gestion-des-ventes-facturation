package com.sales.app.services;

import com.sales.app.Repositories.ClientRepository;
import com.sales.app.entities.Client;
import com.sales.app.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> searchClients(String keyword) {
        if (keyword != null) {
            return clientRepository.search(keyword);
        }
        return findAllClients();
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Client not found with ID %d", id)));
    }

    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Client not found with ID %d", id)));

        clientRepository.delete(client);
    }
    @Override
    public Page<Client> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Client> clients;

        List<Client> allClients = findAllClients();
        if (allClients.size() < startItem) {
            clients = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allClients.size());
            clients = allClients.subList(startItem, toIndex);
        }

        return new PageImpl<>(clients, PageRequest.of(currentPage, pageSize), allClients.size());
    }
}
