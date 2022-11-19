package io.github.curso.mscliente.service;

import io.github.curso.mscliente.model.Client;
import io.github.curso.mscliente.repository.ClientRepository;
import io.github.curso.mscliente.service.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService implements Serializable {

    public final ClientRepository repository;

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    public Optional<Client> findClient(String cpf) {
        return repository.findByCpf(cpf);
    }

    public List<Client> findAllClients() {
        List<Client> clientList = repository.findAll();
        return clientList;
    }

}
