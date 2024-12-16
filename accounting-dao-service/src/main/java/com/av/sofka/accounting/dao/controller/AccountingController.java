package com.av.sofka.accounting.dao.controller;

import com.av.sofka.accounting.dao.client.AccountingClient;
import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.account.Movement;
import com.av.sofka.accounting.dao.service.accounting.AccountingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Validated
@Slf4j
public class AccountingController implements AccountingClient {

    @Autowired
    AccountingService accountingService;

    @Override
    public ResponseEntity<List<Movement>> getAccountMovements(String accountId) {
        return new ResponseEntity<>(accountingService.getAccountMovements(accountId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Movement> getAccountMovement(String accountId, Integer movementId) {
        return new ResponseEntity<>(accountingService.getMovement(accountId, movementId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Movement> saveAccountMovement(String accountId, Movement movement) {
        accountingService.saveAccountMovement(movement, accountId);
        return new ResponseEntity<>(movement, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Movement>> getCustomerMovementsByDates(Integer customerId, Date initDate, Date finalDate) {

        log.info("getCustomerMovementsByDates: - {} - {} - {}", customerId, initDate, finalDate);

        return new ResponseEntity<>(accountingService.getCustomerMovementsByDates(customerId, initDate, finalDate), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Account>> getCustomerAccounts(Integer customerId) {
        return new ResponseEntity<>(accountingService.getAllCustomerAccounts(customerId), HttpStatus.OK);
    }

}
