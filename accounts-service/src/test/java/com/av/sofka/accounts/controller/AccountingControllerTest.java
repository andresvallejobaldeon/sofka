package com.av.sofka.accounts.controller;

import com.av.sofka.accounts.builder.MovementBuilder;
import com.av.sofka.accounts.builder.StatementBuilder;
import com.av.sofka.accounts.model.Movement;
import com.av.sofka.accounts.model.Statement;
import com.av.sofka.accounts.service.AccountingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountingControllerTest {

    @Mock
    private AccountingService accountingService;

    @InjectMocks
    private AccountingController accountingController;

    @Test
    void givenGetAccountMovementsCalledThenShouldReturnMovementsInformation() {

        final List<Movement> expectedMovementList = List.of(MovementBuilder.getMovement());
        when(accountingService.getAccountMovements(any())).thenReturn(expectedMovementList);

        ResponseEntity<List<Movement>> movementListResponse = accountingController.getAccountMovements("1");

        assertThat(movementListResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(movementListResponse.getBody()).isEqualTo(expectedMovementList);

    }

    @Test
    void givenGetCustomerMovementsByDateCalledThenShouldReturnMovementsInformation() {

        final List<Statement> expectedStatemtentList = List.of(StatementBuilder.getStatement());
        when(accountingService.getCustomerMovementsByDates(any(), any(), any())).thenReturn(expectedStatemtentList);

        ResponseEntity<List<Statement>> statementListResponse = accountingController.getCustomerMovementsByDates(1, new Date(), new Date());

        assertThat(statementListResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(statementListResponse.getBody()).isEqualTo(expectedStatemtentList);

    }

}