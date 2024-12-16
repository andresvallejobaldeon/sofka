package com.av.sofka.accounting.dao.controller;

import com.av.sofka.accounting.dao.client.AccountClient;
import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.exception.NonExistingAccountException;
import com.av.sofka.accounting.dao.rest.mapper.AccountMapper;
import com.av.sofka.accounting.dao.rest.model.UpdateAccountRequest;
import com.av.sofka.accounting.dao.service.account.AccountService;
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
        service.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> updateAccount(String accountId, UpdateAccountRequest accountRequest) {
        Account account = service.getById(accountId);
        if (account == null)
            throw new NonExistingAccountException();

        AccountMapper.INSTANCE.updateAccountFromUpdateAccountRequest(accountRequest, account);
        service.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
