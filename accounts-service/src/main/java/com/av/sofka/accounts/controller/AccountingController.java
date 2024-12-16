package com.av.sofka.accounts.controller;

import com.av.sofka.accounts.client.AccountClient;
import com.av.sofka.accounts.client.AccountingClient;
import com.av.sofka.accounts.model.Account;
import com.av.sofka.accounts.model.Movement;
import com.av.sofka.accounts.model.Statement;
import com.av.sofka.accounts.service.AccountingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        return new ResponseEntity<>(accountingService.getAccountMovement(accountId, movementId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Movement> saveAccountMovement(String accountId, Movement movement) {
        return new ResponseEntity<>(accountingService.saveAccountMovement(accountId, movement), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Statement>> getCustomerMovementsByDates(Integer customerId, Date initDate, Date finalDate) {

        log.info("getCustomerMovementsByDates: - {} - {} - {}", customerId, initDate, finalDate);

        return new ResponseEntity<>(
                accountingService.getCustomerMovementsByDates(customerId, initDate, finalDate),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Account>> getCustomerAccounts(Integer customerId) {
        return new ResponseEntity<>(accountingService.getAllCustomerAccounts(customerId), HttpStatus.OK);
    }

}
