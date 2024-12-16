package com.av.sofka.accounting.dao.service.accounting.impl;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.account.Movement;
import com.av.sofka.accounting.dao.model.customer.Customer;
import com.av.sofka.accounting.dao.model.exception.CustomerNotFoundException;
import com.av.sofka.accounting.dao.model.exception.InactiveAccountException;
import com.av.sofka.accounting.dao.model.exception.InsufficientFundsException;
import com.av.sofka.accounting.dao.service.account.AccountService;
import com.av.sofka.accounting.dao.service.account.MovementService;
import com.av.sofka.accounting.dao.service.accounting.AccountingService;
import com.av.sofka.accounting.dao.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountingServiceImpl implements AccountingService {

    @Autowired
    AccountService accountService;

    @Autowired
    MovementService movementService;

    @Autowired
    CustomerService customerService;

    @Override
    @Transactional
    public void saveAccountMovement(Movement movement, String accountId) {
        Account account = accountService.getById(accountId);

        if (!account.getStatus())
            throw new InactiveAccountException();

        movement.setAccount(account);
        movement.setInitialBalance(account.getBalance());
        switch (movement.getMovementType()) {
            case CREDIT:
                account.setBalance(account.getBalance() == null ? 0 : account.getBalance() + movement.getAmount());
                break;
            case DEBIT:
                if (Double.compare(account.getBalance() == null ? 0 : account.getBalance(), movement.getAmount()) < 0)
                    throw new InsufficientFundsException();
                account.setBalance(account.getBalance() == null ? 0 : account.getBalance() - movement.getAmount());
                break;
        }
        movement.setBalance(account.getBalance());
        movementService.save(movement);
        accountService.save(account);
    }

    @Override
    public List<Movement> getAccountMovements(String accountId) {
        Account account = accountService.getById(accountId);
        return movementService.getAllAccountMovements(account);
    }

    @Override
    public Movement getMovement(String accountId, Integer movementId) {
        Account account = accountService.getById(accountId);
        Optional<Movement> optionalMovement = account.getMovements().stream().filter(movement -> movement.getMovementId().equals(movementId)).findFirst();
        return optionalMovement.orElse(null);
    }

    @Override
    public List<Movement> getCustomerMovementsByDates(Integer customerId, Date initDate, Date finalDate) {
        return movementService.getCustomerMovementsByDates(customerId, initDate, finalDate);
    }

    @Override
    public List<Account> getAllCustomerAccounts(Integer customerId) {
        Customer customer = customerService.getById(customerId);

        if (customer == null)
            throw new CustomerNotFoundException();

        return accountService.getAllCustomerAccounts(customer);
    }

}
