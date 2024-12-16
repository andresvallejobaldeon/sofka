package com.av.sofka.accounting.dao.service.accounting.impl;

import com.av.sofka.accounting.dao.builder.AccountBuilder;
import com.av.sofka.accounting.dao.builder.MovementBuilder;
import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.account.Movement;
import com.av.sofka.accounting.dao.model.exception.InsufficientFundsException;
import com.av.sofka.accounting.dao.service.account.AccountService;
import com.av.sofka.accounting.dao.service.account.MovementService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountingServiceImplTest {

    @Mock
    private AccountService accountService;

    @Mock
    private MovementService movementService;

    @InjectMocks
    private AccountingServiceImpl accountingService;

    @SneakyThrows
    @Test
    void givenSaveAccountMovementCalledWithCreditValueThenShouldUpdateAccountBalance() {
        final Movement movement = MovementBuilder.getCreditMovement();
        Account account = AccountBuilder.getAccount();
        when(accountService.getById(any())).thenReturn(account);

        accountingService.saveAccountMovement(movement, account.getAccountNumber());

        assertThat(account.getInitialBalance()).isEqualTo(0d);
        assertThat(account.getBalance()).isEqualTo(movement.getAmount());

    }

    @SneakyThrows
    @Test
    void givenSaveAccountMovementCalledWithDebitValueAndInsufficientFundsThenShouldThrowException() {
        final Movement movement = MovementBuilder.getDebitMovement();
        Account account = AccountBuilder.getAccount();
        when(accountService.getById(any())).thenReturn(account);

        assertThrows(InsufficientFundsException.class,
                () -> accountingService.saveAccountMovement(movement, account.getAccountNumber()));
    }

    @SneakyThrows
    @Test
    void givenSaveAccountMovementCalledWithDebitValueThenShouldUpdateAccountBalance() {
        final Movement creditMovement = MovementBuilder.getCreditMovement();
        final Movement debitMovement = MovementBuilder.getDebitMovement();
        Account account = AccountBuilder.getAccount();
        when(accountService.getById(any())).thenReturn(account);

        accountingService.saveAccountMovement(creditMovement, account.getAccountNumber());
        accountingService.saveAccountMovement(debitMovement, account.getAccountNumber());

        assertThat(account.getInitialBalance()).isEqualTo(0d);
        assertThat(account.getBalance()).isEqualTo(creditMovement.getAmount() - debitMovement.getAmount());
    }

}