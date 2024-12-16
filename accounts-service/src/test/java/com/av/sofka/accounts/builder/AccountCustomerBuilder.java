package com.av.sofka.accounts.builder;

import com.av.sofka.accounts.model.AccountCustomer;

public class AccountCustomerBuilder {

    public static AccountCustomer getAccountCustomer() {

        return AccountCustomer.builder()
                .customerId(1)
                .age(30)
                .phone("11223344")
                .name("Michael Jackson")
                .gender("Masculino")
                .address("Av Balboa")
                .identification("1020304050")
                .build();

    }

}
