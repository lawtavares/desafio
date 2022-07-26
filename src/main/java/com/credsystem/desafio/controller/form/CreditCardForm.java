package com.credsystem.desafio.controller.form;

import com.credsystem.desafio.model.CreditCard;

import javax.validation.constraints.*;

public class CreditCardForm {

    @NotNull @NotEmpty @Size(max = 16, min = 16)
    private String number;
    @Max(999) @Min(100)
    private int securityCode;
    @NotNull @NotEmpty @Size(max = 5, min = 5)
    private String expirationDate;
    @Max(9999) @Min(1000)
    private int pinCode;
    @NotNull
    private Long ownerId;

    public CreditCard convert(){
        return new CreditCard(this.number, this.securityCode, this.expirationDate, this.pinCode);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
