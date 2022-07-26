package com.credsystem.desafio.controller;

import com.credsystem.desafio.controller.dto.ClientDTO;
import com.credsystem.desafio.controller.form.ClientForm;
import com.credsystem.desafio.controller.form.UpdateClientForm;
import com.credsystem.desafio.model.Client;
import com.credsystem.desafio.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(ClientDTO.convertList(clients));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClientDTO> addClient(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder uriBuilder){
        try{
            Client client = clientForm.convert();
            Client clientByCpf = clientRepository.findByCpf(client.getCpf());
            if(clientByCpf != null)
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            clientRepository.save(client);
            URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
            return ResponseEntity.created(uri).body(new ClientDTO(client));
        } catch (Exception ne){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id){
        Optional<Client> optClient = clientRepository.findById(id);
        return optClient.map(client -> ResponseEntity.ok(new ClientDTO(client))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removeClient(@PathVariable Long id) {
        Optional<Client> optClient = clientRepository.findById(id);
        if (optClient.isPresent()) {
            clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody @Valid UpdateClientForm form) {
        Optional<Client> optional = clientRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Client updatedClient = form.updateClient(optional.get());

        return ResponseEntity.ok(new ClientDTO(updatedClient));
    }
}
