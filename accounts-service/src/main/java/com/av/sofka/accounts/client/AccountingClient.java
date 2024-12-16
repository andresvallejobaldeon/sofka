package com.av.sofka.accounts.client;

import com.av.sofka.accounts.model.Account;
import com.av.sofka.accounts.model.Movement;
import com.av.sofka.accounts.model.Statement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("")
public interface AccountingClient {

    @GetMapping("/account/{accountId}/movement")
    ResponseEntity<List<Movement>> getAccountMovements(@PathVariable String accountId);

    @GetMapping("/account/{accountId}/movement/{movementId}")
    ResponseEntity<Movement> getAccountMovement(@PathVariable String accountId, @PathVariable Integer movementId);

    @PostMapping("/account/{accountId}/movement")
    ResponseEntity<Movement> saveAccountMovement(@PathVariable String accountId, @RequestBody Movement movement);

    @GetMapping("/customer/{customerId}/movement")
    ResponseEntity<List<Statement>> getCustomerMovementsByDates(@PathVariable Integer customerId
            , @RequestParam("init-date") Date initDate
            , @RequestParam("final-date") Date finalDate);

    @GetMapping("/customer/{customerId}/account")
    ResponseEntity<List<Account>> getCustomerAccounts(@PathVariable Integer customerId);

}
