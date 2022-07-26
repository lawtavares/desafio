package com.credsystem.desafio.service;

import com.credsystem.desafio.controller.form.CreditCardForm;
import com.credsystem.desafio.model.Client;
import com.credsystem.desafio.model.CreditCard;
import com.credsystem.desafio.repository.ClientRepository;
import com.credsystem.desafio.repository.CreditCardRepository;
import com.credsystem.desafio.utils.CreditCardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Long save(CreditCardForm creditCardForm){
        CreditCard card = creditCardForm.convert();
        Optional<Client> opt = clientRepository.findById(creditCardForm.getOwnerId());
        if(!opt.isPresent())
            throw new NoSuchElementException();
        Client client = opt.get();
        card.setOwner(client);
        BigDecimal clientLimit = CreditCardUtils.setClientCreditLimit(client);
        card.setCreditLimit(clientLimit);
        card.setAvailableLimit(clientLimit);

        creditCardRepository.save(card);
        return card.getId();
    }
}
