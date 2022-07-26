package com.credsystem.desafio.controller.dto;

import com.credsystem.desafio.model.CreditCard;

public class DetailedCreditCardDTO {

    private Long id;
    private String creditLimit;
    private String availableLimit;
    private String number;
    private String expirationDate;
    private String owner;
    private int securityCode;

    public DetailedCreditCardDTO(CreditCard card){
        this.id = card.getId();
        this.creditLimit = "R$ ".concat(card.getCreditLimit().toPlainString());
        this.availableLimit = "R$ ".concat(card.getAvailableLimit().toPlainString());
        this.number = card.getNumber();
        this.expirationDate = card.getExpirationDate();
        this.owner = card.getOwner().getName().concat(" ").concat(card.getOwner().getSurname());
        this.securityCode = card.getSecurityCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(String availableLimit) {
        this.availableLimit = availableLimit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
