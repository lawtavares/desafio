package com.credsystem.desafio.service;

import com.credsystem.desafio.controller.form.TransactionForm;
import com.credsystem.desafio.exception.NotEnoughCreditException;
import com.credsystem.desafio.exception.WrongPinCodeException;
import com.credsystem.desafio.model.CreditCard;
import com.credsystem.desafio.model.Transaction;
import com.credsystem.desafio.repository.ClientRepository;
import com.credsystem.desafio.repository.CreditCardRepository;
import com.credsystem.desafio.repository.TransactionRepository;
import com.credsystem.desafio.utils.CreditCardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Long save(TransactionForm transactionForm) throws NotEnoughCreditException, WrongPinCodeException {
        Optional<CreditCard> optional = creditCardRepository.findById(transactionForm.getCardId());
        if(!optional.isPresent())
            throw new NoSuchElementException();
        CreditCard card = optional.get();
        Transaction transaction = transactionForm.convert();
        transaction.setCard(card);

        if(transaction.getPinCode() != card.getPinCode())
            throw new WrongPinCodeException();

        BigDecimal newAvailableLimit = CreditCardUtils.newLimitAfterTransaction(card, transaction.getAmount());

        if(newAvailableLimit.doubleValue() < 0)
            throw new NotEnoughCreditException();

        card.setAvailableLimit(newAvailableLimit);

        transactionRepository.save(transaction);
        creditCardRepository.save(card);

        return transaction.getId();
    }
}
