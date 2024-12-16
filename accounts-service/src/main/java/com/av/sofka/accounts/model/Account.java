package com.av.sofka.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Double balance;
    private Boolean status;
    private AccountCustomer customer;

}
