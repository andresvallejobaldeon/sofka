package com.av.sofka.accounts.builder;

import com.av.sofka.accounts.model.Movement;
import com.av.sofka.accounts.model.MovementType;

import java.util.Date;

public class MovementBuilder {

    public static Movement getMovement() {
        return Movement.builder()
                .movementId(1)
                .movementDate(new Date(2024, 12, 13))
                .movementType(MovementType.CREDIT)
                .account(AccountBuilder.getAccount())
                .amount(50d)
                .initialBalance(0d)
                .balance(50d)
                .build();
    }

}
