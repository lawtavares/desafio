package com.credsystem.desafio.controller.form;

import com.credsystem.desafio.model.Transaction;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class TransactionForm {

    @NotNull
    private Long cardId;
    @NotNull @NotEmpty @DecimalMin("0.00")
    private String amount;
    @NotNull @Max(9999) @Min(1000)
    private int pinCode;

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Transaction convert(){
        return new Transaction(new BigDecimal(amount), pinCode);
    }
}
