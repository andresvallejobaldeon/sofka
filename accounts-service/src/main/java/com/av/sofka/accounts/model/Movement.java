package com.av.sofka.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movement {

    private Integer movementId;
    private Account account;
    private Date movementDate;
    private MovementType movementType;
    private Double amount;
    private Double balance;
    private Double initialBalance;

}
