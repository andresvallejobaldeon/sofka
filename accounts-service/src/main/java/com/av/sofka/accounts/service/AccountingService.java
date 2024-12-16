package com.av.sofka.accounts.service;

import com.av.sofka.accounts.model.Account;
import com.av.sofka.accounts.model.Movement;
import com.av.sofka.accounts.model.Statement;

import java.util.Date;
import java.util.List;

public interface AccountingService {

    List<Movement> getAccountMovements(String accountId);
    Movement getAccountMovement(String accountId, Integer movementId);
    Movement saveAccountMovement(String accountId, Movement movement);
    List<Statement> getCustomerMovementsByDates(Integer customerId, Date initDate, Date finalDate);
    List<Account> getAllCustomerAccounts (Integer customerId);

}
