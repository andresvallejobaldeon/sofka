package com.av.sofka.accounts.service.impl;

import com.av.sofka.accounts.builder.MovementBuilder;
import com.av.sofka.accounts.builder.StatementBuilder;
import com.av.sofka.accounts.config.GatewayDaoClientConfiguration;
import com.av.sofka.accounts.model.Movement;
import com.av.sofka.accounts.model.Statement;
import com.av.sofka.accounts.model.exception.AccountingDaoServiceException;
import com.av.sofka.accounts.model.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountingServiceImplTest {

    private static MockWebServer mockWebServer;
    private AccountingServiceImpl accountingService;

    ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void shutDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        GatewayDaoClientConfiguration configuration = new GatewayDaoClientConfiguration();
        configuration.setDaoUrl("http://localhost:" + mockWebServer.getPort());
        configuration.setApiCustomerAccount("/customer/{id}/account");
        configuration.setApiMovement("/account/{id}/movement");
        configuration.setApiStatement("/customer/{id}/movement");

        accountingService = new AccountingServiceImpl(configuration);
    }

    @Test
    void givenGetAccountMovementsThenShouldReturnAccountMovementsInformation() {
        final List<Movement> expectedMovementList = List.of(MovementBuilder.getMovement());
        Dispatcher dispatcher = new Dispatcher() {
            @NotNull
            @SneakyThrows
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                return new MockResponse()
                        .addHeader("Content-Type", "application/json")
                        .setBody(objectMapper.writeValueAsString(expectedMovementList))
                        .setResponseCode(HttpStatus.OK.value());
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        List<Movement> movementList = accountingService.getAccountMovements("1");

        assertThat(movementList).isEqualTo(expectedMovementList);

    }

    @Test
    void givenGetAccountMovementThenShouldReturnAccountMovementInformation() {
        final Movement expectedMovement = MovementBuilder.getMovement();
        Dispatcher dispatcher = new Dispatcher() {
            @NotNull
            @SneakyThrows
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                return new MockResponse()
                        .addHeader("Content-Type", "application/json")
                        .setBody(objectMapper.writeValueAsString(expectedMovement))
                        .setResponseCode(HttpStatus.OK.value());
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        Movement movement = accountingService.getAccountMovement("1", 1);

        assertThat(movement).isEqualTo(expectedMovement);

    }

    @Test
    void givenGetCustomerMovementsByDateThenShouldReturnMovementsInformation() {
        final List<Statement> expectedStatementList = List.of(StatementBuilder.getStatement());
        final List<Movement> movementList = List.of(MovementBuilder.getMovement());
        Dispatcher dispatcher = new Dispatcher() {
            @NotNull
            @SneakyThrows
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                return new MockResponse()
                        .addHeader("Content-Type", "application/json")
                        .setBody(objectMapper.writeValueAsString(movementList))
                        .setResponseCode(HttpStatus.OK.value());
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        List<Statement> statementList = accountingService.getCustomerMovementsByDates(1, new Date(), new Date());

        assertThat(statementList).isEqualTo(expectedStatementList);

    }
    @Test
    void givenGetCustomerMovementsByDateCalledWhenHasErrorsThenShouldThrowAccountingDaoServiceException() {
        final ApiError expectedApiError = ApiError.builder().message("Error").status(HttpStatus.BAD_REQUEST).build();
        Dispatcher dispatcher = new Dispatcher() {
            @NotNull
            @SneakyThrows
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                return new MockResponse()
                        .addHeader("Content-Type", "application/json")
                        .setResponseCode(HttpStatus.BAD_REQUEST.value())
                        .setBody(objectMapper.writeValueAsString(expectedApiError));
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(AccountingDaoServiceException.class, () -> accountingService.getCustomerMovementsByDates(1, new Date(), new Date()));
    }

}