package com.av.sofka.accounting.dao.service.customer;

import com.av.sofka.accounting.dao.model.customer.Customer;

import java.util.List;

public interface CustomerService {

    void save(Customer customer);
    List<Customer> getAll();
    Customer getById (Integer customerId);
    void delete (Integer customerId);

}
