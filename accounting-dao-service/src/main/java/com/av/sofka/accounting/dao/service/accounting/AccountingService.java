package com.av.sofka.accounting.dao.service.accounting;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.account.Movement;
import com.av.sofka.accounting.dao.model.customer.Customer;

import java.util.Date;
import java.util.List;

public interface AccountingService {

    void saveAccountMovement(Movement movement, String accountId);
    List<Movement> getAccountMovements(String accountId);
    Movement getMovement(String accountId, Integer movementId);
    List<Movement> getCustomerMovementsByDates(Integer customerId, Date initDate, Date finalDate);
    List<Account> getAllCustomerAccounts (Integer customerId);

}
