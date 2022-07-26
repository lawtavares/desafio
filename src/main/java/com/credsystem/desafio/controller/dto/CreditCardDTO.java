package com.credsystem.desafio.controller.dto;

import com.credsystem.desafio.model.CreditCard;
import java.util.List;
import java.util.stream.Collectors;

public class CreditCardDTO {

    private Long id;
    private String number;
    private String expirationDate;
    private String owner;

    public CreditCardDTO(CreditCard card){
        this.id = card.getId();
        this.number = card.getNumber();
        this.expirationDate = card.getExpirationDate();
        this.owner = card.getOwner().getName().concat(" ").concat(card.getOwner().getSurname());
    }

    public static List<CreditCardDTO> convertList(List<CreditCard> cardList){
        return cardList.stream().map(CreditCardDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
