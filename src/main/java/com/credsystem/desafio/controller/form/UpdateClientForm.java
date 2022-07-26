package com.credsystem.desafio.controller.form;

import com.credsystem.desafio.model.Client;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class UpdateClientForm {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String surname;

    @NotNull @NotEmpty @DecimalMin("0.00")
    private String salary;

    @NotNull @NotEmpty
    private String address;

    public Client updateClient(Client client){
        client.setName(this.name);
        client.setSurname(this.surname);
        client.setAddress(this.address);
        client.setSalary(new BigDecimal(this.salary));
        return client;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
