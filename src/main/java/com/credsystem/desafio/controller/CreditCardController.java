package com.credsystem.desafio.controller;

import com.credsystem.desafio.controller.dto.CreditCardDTO;
import com.credsystem.desafio.controller.dto.DetailedCreditCardDTO;
import com.credsystem.desafio.controller.form.CreditCardForm;
import com.credsystem.desafio.model.CreditCard;
import com.credsystem.desafio.repository.CreditCardRepository;
import com.credsystem.desafio.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CreditCardController {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards(){
        List<CreditCard> cards = creditCardRepository.findAll();
        return ResponseEntity.ok(CreditCardDTO.convertList(cards));
    }

    @PostMapping
    public ResponseEntity<CreditCardDTO> addCard(@RequestBody @Valid CreditCardForm creditCardForm, UriComponentsBuilder uriBuilder){
        try{
            Long cardId = creditCardService.save(creditCardForm);
            URI uri = uriBuilder.path("/card/{id}").buildAndExpand(cardId).toUri();
            return ResponseEntity.created(uri).body(new CreditCardDTO(creditCardRepository.getReferenceById(cardId)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedCreditCardDTO> getCard(@PathVariable Long id){
        Optional<CreditCard> optCard = creditCardRepository.findById(id);
        return optCard.map(card -> ResponseEntity.ok(new DetailedCreditCardDTO(card))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removeCard(@PathVariable Long id) {
        Optional<CreditCard> optClient = creditCardRepository.findById(id);
        if (optClient.isPresent()) {
            creditCardRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
