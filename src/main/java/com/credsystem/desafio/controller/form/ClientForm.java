package com.credsystem.desafio.controller.form;

import com.credsystem.desafio.model.Client;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class ClientForm {

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String surname;

    @NotNull
    private Date birthdate;

    @NotNull @NotEmpty @DecimalMin("1000.00")
    private String salary;

    @NotNull @NotEmpty @Size(min = 11, max = 11)
    private String cpf;

    @NotNull @NotEmpty
    private String address;

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

    public Client convert() {
        return new Client(this.name, this.surname, this.cpf, this.salary, this.birthdate, this.address);
    }
}
