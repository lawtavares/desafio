package com.credsystem.desafio.repository;

import com.credsystem.desafio.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCpf(String cpf);
}
