package com.av.sofka.accounting.dao.builder;

import com.av.sofka.accounting.dao.model.customer.Customer;

import java.util.List;

public class CustomerBuilder {

    public static Customer getCustomer() {
        return Customer.builder()
                .customerId(1)
                .name("Andres")
                .address("Sangolqui")
                .age(43)
                .phone("123456")
                .gender("Masculino")
                .identification("1234567890")
                .build();
    }

    public static List<Customer> getAllCustomers() {
        return List.of(
                Customer.builder()
                        .customerId(1)
                        .name("Andres")
                        .address("Sangolqui")
                        .age(43)
                        .phone("123456")
                        .gender("Masculino")
                        .identification("1234567890")
                        .build(),
                Customer.builder()
                        .customerId(2)
                        .name("Patricio")
                        .address("Quito")
                        .age(41)
                        .phone("112233")
                        .gender("Masculino")
                        .identification("0987654321")
                        .build()
        );

    }

}
