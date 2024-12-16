package com.av.sofka.accounting.dao.service.customer.impl;

import com.av.sofka.accounting.dao.builder.CustomerBuilder;
import com.av.sofka.accounting.dao.model.customer.Customer;
import com.av.sofka.accounting.dao.repository.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    void givenGetAllCalledThenShouldReturnAllCustomers() {
        final List<Customer> expectedCustomerList = CustomerBuilder.getAllCustomers();
        when(customerRepository.findAll()).thenReturn(CustomerBuilder.getAllCustomers());

        List<Customer> customerList = service.getAll();

        assertThat(customerList).isEqualTo(expectedCustomerList);
    }

    @Test
    void givenGetByIdCalledWithExistingCustomerThenShouldReturnCustomer() {
        final Customer expectedCustomer = CustomerBuilder.getCustomer();
        when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(CustomerBuilder.getCustomer()));

        Customer customer = service.getById(1);

        assertThat(customer).isEqualTo(expectedCustomer);
    }

}