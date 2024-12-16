package com.av.sofka.accounting.dao.service.account.impl;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.customer.Customer;
import com.av.sofka.accounting.dao.repository.account.AccountRepository;
import com.av.sofka.accounting.dao.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public void save(Account account) {
        repository.save(account);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Account getById(String accountId) {
        Optional<Account> optional = repository.findById(accountId);
        return optional.orElse(null);
    }

    @Override
    public void delete(String accountId) {
        if (repository.existsById(accountId))
            repository.deleteById(accountId);
    }

    @Override
    public List<Account> getAllCustomerAccounts(Customer customer) {
        return repository.findByCustomer(customer);
    }

}
