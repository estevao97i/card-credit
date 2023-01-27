package io.github.curso.mscliente.resource;

import io.github.curso.mscliente.model.Client;
import io.github.curso.mscliente.service.ClientService;
import io.github.curso.mscliente.service.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientResource {

    private final ClientService service;

//    @GetMapping
//    public String status() {
//        return "Ok...";
//    }

    @PostMapping
    public ResponseEntity<Client> insertClient(@RequestBody ClientDto dto) {

        Client client = dto.toModel();
        service.saveClient(client);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();

        return ResponseEntity.created((headerLocation)).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity findByCpf(@RequestParam("cpf") String cpf) {
        Optional<Client> client = service.findClient(cpf);

        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity findAllClients() {
        log.info("Entering find all clients method");
        List<Client> client = service.findAllClients();

//        if (client.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }

        return ResponseEntity.ok(client);
    }

}
