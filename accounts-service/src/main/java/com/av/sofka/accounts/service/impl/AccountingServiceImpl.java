package com.av.sofka.accounts.service.impl;

import com.av.sofka.accounts.config.GatewayDaoClientConfiguration;
import com.av.sofka.accounts.mapper.StatementMapper;
import com.av.sofka.accounts.model.Account;
import com.av.sofka.accounts.model.Movement;
import com.av.sofka.accounts.model.Statement;
import com.av.sofka.accounts.model.exception.AccountingDaoServiceException;
import com.av.sofka.accounts.model.exception.ApiError;
import com.av.sofka.accounts.service.AccountingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AccountingServiceImpl implements AccountingService {

    private final WebClient webClient;
    private final String apiMovement;
    private final String apiStatement;
    private final String apiCustomerAcount;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public AccountingServiceImpl(GatewayDaoClientConfiguration configuration) {
        webClient = WebClient.create(configuration.getDaoUrl());
        apiStatement = configuration.getApiStatement();
        apiMovement = configuration.getApiMovement();
        apiCustomerAcount = configuration.getApiCustomerAccount();
    }

    @Override
    public List<Movement> getAccountMovements(String accountId) {

        log.info("send DAO getAccountMovementsRequest: - {}", accountId);

        return webClient.get()
                .uri(apiMovement.replace("{id}", accountId))
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToFlux(Movement.class);
                    return clientResponse.bodyToFlux(ApiError.class)
                            .flatMap(body -> Flux.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .collectList()
                .block();
    }

    @Override
    public Movement getAccountMovement(String accountId, Integer movementId) {

        log.info("send DAO getAccountMovementRequest: - {} - {}", accountId, movementId);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiMovement.replace("{id}", accountId) + "/{id}")
                        .build(movementId))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToMono(Movement.class);
                    return clientResponse.bodyToMono(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .block();
    }

    @Override
    public Movement saveAccountMovement(String accountId, Movement movement) {

        log.info("send DAO saveAccountMovementRequest: - {} - {}", accountId, movement);

        return webClient.patch()
                .uri(apiMovement.replace("{id}", accountId))
                .body(Mono.just(movement), Movement.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToMono(Movement.class);
                    return clientResponse.bodyToMono(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .block();
    }

    @Override
    public List<Statement> getCustomerMovementsByDates(Integer customerId, Date initDate, Date finalDate) {

        log.info("send DAO getCustomerMovementsByDatesRequest: - {} - {} - {}", customerId, initDate, finalDate);

        List<Movement> movementList = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiStatement.replace("{id}", customerId.toString()))
                        .queryParam("init-date", dateFormat.format(initDate))
                        .queryParam("final-date", dateFormat.format(finalDate))
                        .build())
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToFlux(Movement.class);
                    return clientResponse.bodyToFlux(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .collectList()
                .block();

        if (movementList == null || movementList.isEmpty())
            return Collections.emptyList();

        return StatementMapper.INSTANCE.toStatementList(movementList);
    }

    @Override
    public List<Account> getAllCustomerAccounts(Integer customerId) {

        log.info("send DAO getAllCustomerAccounts: - {}", customerId);

        return webClient.get()
                .uri(apiCustomerAcount.replace("{id}", customerId.toString()))
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToFlux(Account.class);
                    return clientResponse.bodyToFlux(ApiError.class)
                            .flatMap(body -> Flux.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .collectList()
                .block();
    }

}
