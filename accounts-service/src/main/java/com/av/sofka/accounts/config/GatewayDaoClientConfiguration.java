package com.av.sofka.accounts.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class GatewayDaoClientConfiguration {

    @Value("${spring.application.endpoint.dao.url}")
    private String daoUrl;

    @Value("${spring.application.endpoint.dao.api.account}")
    private String apiAccount;

    @Value("${spring.application.endpoint.dao.api.movement}")
    private String apiMovement;

    @Value("${spring.application.endpoint.dao.api.statement}")
    private String apiStatement;

    @Value("${spring.application.endpoint.dao.api.customer.account}")
    private String apiCustomerAccount;

}
