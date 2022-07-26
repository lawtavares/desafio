package com.credsystem.desafio.controller.dto;

import com.credsystem.desafio.model.CreditCard;
import com.credsystem.desafio.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {

    private Long id;
    private Long cardId;
    private BigDecimal amount;
    private Date date;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.cardId = transaction.getCard().getId();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
