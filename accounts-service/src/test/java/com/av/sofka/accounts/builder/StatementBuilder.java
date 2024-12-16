package com.av.sofka.accounts.builder;

import com.av.sofka.accounts.model.Statement;

import java.util.Date;

public class StatementBuilder {

    public static Statement getStatement() {

        return Statement.builder()
                .accountType("Corriente")
                .balance(50d)
                .movementDate(new Date(2024, 12, 13))
                .initialBalance(0d)
                .accountNumber("123456")
                .customerName("Michael Jackson")
                .status(true)
                .movementAmount(50d)
                .build();
    }

}
