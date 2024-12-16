package com.av.sofka.customer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class GatewayDaoClientConfiguration {

    @Value("${spring.application.endpoint.dao.url}")
    private String daoUrl;

    @Value("${spring.application.endpoint.dao.api.customer}")
    private String apiCustomer;

}
