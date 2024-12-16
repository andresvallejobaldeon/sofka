package com.av.sofka.customer.service;

import com.av.sofka.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);
    List<Customer> getAll();
    Customer getById (Integer customerId);
    Customer updateCustomer(Customer customer, Integer customerId);

}
