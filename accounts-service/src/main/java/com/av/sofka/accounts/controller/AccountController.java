package com.av.sofka.accounts.controller;

import com.av.sofka.accounts.client.AccountClient;
import com.av.sofka.accounts.model.Account;
import com.av.sofka.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class AccountController implements AccountClient {

    @Autowired
    AccountService service;

    @Override
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> getAccount(String accountId) {
        return new ResponseEntity<>(service.getById(accountId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> saveAccount(Account account) {
        return new ResponseEntity<>(service.save(account), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> updateAccount(String accountId, Account accountRequest) {
        return new ResponseEntity<>(service.update(accountId, accountRequest), HttpStatus.OK);
    }

}
