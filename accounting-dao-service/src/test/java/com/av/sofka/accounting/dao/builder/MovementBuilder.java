package com.av.sofka.accounting.dao.builder;

import com.av.sofka.accounting.dao.model.account.Movement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.av.sofka.accounting.dao.model.account.MovementType.CREDIT;
import static com.av.sofka.accounting.dao.model.account.MovementType.DEBIT;

public class MovementBuilder {

    private static final String STRING_DATE = "2024-11-17";
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Movement getCreditMovement() throws ParseException {
        return Movement.builder()
                .movementId(1)
                .movementDate(formatter.parse(STRING_DATE))
                .movementType(CREDIT)
                .amount(100d)
                .build();
    }

    public static Movement getDebitMovement() throws ParseException {
        return Movement.builder()
                .movementId(2)
                .movementDate(formatter.parse(STRING_DATE))
                .movementType(DEBIT)
                .amount(50d)
                .build();
    }

}
