package com.av.sofka.accounts.service;

import com.av.sofka.accounts.model.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);
    List<Account> getAll();
    Account getById (String accountId);
    Account update (String accountId, Account account);


}
