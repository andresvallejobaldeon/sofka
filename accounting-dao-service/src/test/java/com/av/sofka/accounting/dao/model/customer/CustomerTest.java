package com.av.sofka.accounting.dao.model.customer;

import com.av.sofka.accounting.dao.builder.CustomerBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CustomerTest {

    @Test
    void givenNewCustomerThenShouldHasDefinedStructure() {
        final Customer customer = CustomerBuilder.getCustomer();

        assertThat(customer.getCustomerId()).getClass().equals(Integer.class);
        assertThat(customer.getName()).getClass().equals(String.class);
        assertThat(customer.getGender()).getClass().equals(String.class);
        assertThat(customer.getAge()).getClass().equals(Integer.class);
        assertThat(customer.getIdentification()).getClass().equals(String.class);
        assertThat(customer.getAddress()).getClass().equals(String.class);
        assertThat(customer.getPhone()).getClass().equals(String.class);
        assertThat(customer.getPassword()).getClass().equals(String.class);
        assertThat(customer.getStatus()).getClass().equals(Boolean.class);

    }

}