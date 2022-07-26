package com.credsystem.desafio.controller;

import com.credsystem.desafio.controller.dto.TransactionDTO;
import com.credsystem.desafio.controller.form.TransactionForm;
import com.credsystem.desafio.exception.WrongPinCodeException;
import com.credsystem.desafio.repository.TransactionRepository;
import com.credsystem.desafio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody @Valid TransactionForm transactionForm, UriComponentsBuilder uriBuilder){
        try{
            Long transactionId = transactionService.save(transactionForm);
            URI uri = uriBuilder.path("/card/{id}").buildAndExpand(transactionId).toUri();
            return ResponseEntity.created(uri).body(new TransactionDTO(transactionRepository.getReferenceById(transactionId)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }catch (WrongPinCodeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
