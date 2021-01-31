package pl.pjatk.gameplay.service;


import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Client;
import pl.pjatk.gameplay.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findByID(Long playerId) {
        if (playerId == 10L) {
            throw new RuntimeException();
        } else {
            return clientRepository.findById(playerId);
        }
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public Client update(Client client) {
        Optional<Client> byId = clientRepository.findById(client.getId());
        if (byId.isEmpty()) {
            throw new RuntimeException();
        } else {
            return clientRepository.save(client);
        }
    }

    public void deleteAll() {
        clientRepository.deleteAll();
    }

}
