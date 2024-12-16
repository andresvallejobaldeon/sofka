package com.av.sofka.customer.service.impl;

import com.av.sofka.customer.config.GatewayDaoClientConfiguration;
import com.av.sofka.customer.model.Customer;
import com.av.sofka.customer.model.exception.AccountingDaoServiceException;
import com.av.sofka.customer.model.exception.ApiError;
import com.av.sofka.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final WebClient webClient;
    private final String apiCustomer;

    public CustomerServiceImpl(GatewayDaoClientConfiguration configuration) {
        webClient = WebClient.create(configuration.getDaoUrl());
        apiCustomer = configuration.getApiCustomer();
    }

    @Override
    public Customer save(Customer customer) {

        log.info("send DAO saveCustomerRequest: - {}", customer);

        return webClient.post()
                .uri(apiCustomer)
                .body(Mono.just(customer), Customer.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToMono(Customer.class);
                    return clientResponse.bodyToMono(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .block();
    }

    @Override
    public List<Customer> getAll() {

        log.info("send DAO getAllCustomers...");

        return webClient.get()
                .uri(apiCustomer)
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToFlux(Customer.class);
                    return clientResponse.bodyToFlux(ApiError.class)
                            .flatMap(body -> Flux.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .collectList()
                .block();
    }

    @Override
    public Customer getById(Integer customerId) {

        log.info("send DAO getCustomer: - {}", customerId);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiCustomer + "/{id}")
                        .build(customerId))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToMono(Customer.class);
                    return clientResponse.bodyToMono(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .block();
    }

    @Override
    public Customer updateCustomer(Customer customer, Integer customerId) {

        log.info("send DAO updateCustomerRequest: - {} - {}", customerId, customer);

        return webClient.patch()
                .uri(uriBuilder -> uriBuilder
                        .path(apiCustomer + "/{id}")
                        .build(customerId))
                .body(Mono.just(customer), Customer.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK))
                        return clientResponse.bodyToMono(Customer.class);
                    return clientResponse.bodyToMono(ApiError.class)
                            .flatMap(body -> Mono.error(new AccountingDaoServiceException(body.getMessage(), body.getStatus())));
                })
                .block();
    }

}
