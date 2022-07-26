package com.credsystem.desafio.controller.dto;

import com.credsystem.desafio.model.Client;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long id;
    private String name;
    private String surname;
    private Date birthdate;
    private String salary;
    private String cpf;
    private String address;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.address = client.getAddress();
        this.cpf = client.getCpf();
        this.birthdate = client.getBirthdate();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.salary = "R$ ".concat(client.getSalary().toPlainString());
    }

    public static List<ClientDTO> convertList(List<Client> clientList) {
        return clientList.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
