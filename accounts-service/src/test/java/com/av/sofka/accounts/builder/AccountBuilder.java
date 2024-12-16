package com.av.sofka.accounts.builder;

import com.av.sofka.accounts.model.Account;

public class AccountBuilder {

    public static Account getAccount() {
        return Account.builder()
                .accountNumber("123456")
                .accountType("Corriente")
                .balance(50d)
                .status(true)
                .customer(AccountCustomerBuilder.getAccountCustomer())
                .initialBalance(0d)
                .build();
    }
}
