package com.av.sofka.accounts.service.impl;

import com.av.sofka.accounts.config.GatewayDaoClientConfiguration;
import com.av.sofka.accounts.model.Account;
import com.av.sofka.accounts.model.exception.AccountingDaoServiceException;
import com.av.sofka.accounts.model.exception.ApiError;
import com.av.sofka.accounts.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final WebClient webClient;
    private final String apiAccount;

    public AccountServiceImpl(GatewayDaoClientConfiguration configuration) {
        webClient = WebClient.create(configuration.getDaoUrl());
        apiAccount = configuration.getApiAccount();
    }

    @Override
    public Account save(Account account) {

        log.info("send DAO saveAccountRequest: - {}", account);

        account.setBalance(account.getInitialBalance());

        return webClient.post()
                .uri(apiAccount)
                .body(Mono.just(account), Account.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToMono(Account.class);
                    return clientResponse.bodyToMono(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .block();
    }

    @Override
    public List<Account> getAll() {

        log.info("send DAO getAllAccounts...");

        return webClient.get()
                .uri(apiAccount)
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToFlux(Account.class);
                    return clientResponse.bodyToFlux(ApiError.class)
                            .flatMap(body -> Flux.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .collectList()
                .block();
    }

    @Override
    public Account getById(String accountId) {

        log.info("send DAO getAccount: - {}", accountId);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiAccount + "/{id}")
                        .build(accountId))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToMono(Account.class);
                    return clientResponse.bodyToMono(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .block();
    }

    @Override
    public Account update(String accountId, Account account) {

        log.info("send DAO updateAccount: - {} - {}", accountId, account);

        return webClient.patch()
                .uri(uriBuilder -> uriBuilder
                        .path(apiAccount + "/{id}")
                        .build(accountId))
                .body(Mono.just(account), Account.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToMono(Account.class);
                    return clientResponse.bodyToMono(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .block();
    }

}
