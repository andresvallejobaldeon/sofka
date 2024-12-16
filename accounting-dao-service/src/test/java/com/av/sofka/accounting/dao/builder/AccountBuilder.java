package com.av.sofka.accounting.dao.builder;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.customer.Customer;

public class AccountBuilder {

    private static final Customer NEW_CUSTOMER = CustomerBuilder.getCustomer();

    public static Account getAccount() {
        return Account.builder()
                .accountNumber("123456")
                .accountType("Ahorros")
                .balance(0d)
                .initialBalance(0d)
                .status(true)
                .customer(NEW_CUSTOMER)
                .build();
    }
}
