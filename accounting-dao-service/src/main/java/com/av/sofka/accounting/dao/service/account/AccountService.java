package com.av.sofka.accounting.dao.service.account;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.customer.Customer;

import java.util.List;

public interface AccountService {

    void save(Account account);
    List<Account> getAll();
    Account getById (String accountId);
    void delete (String accountId);
    List<Account> getAllCustomerAccounts(Customer customer);

}
