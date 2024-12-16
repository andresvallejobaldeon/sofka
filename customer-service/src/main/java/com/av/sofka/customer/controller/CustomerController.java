package com.av.sofka.customer.controller;

import com.av.sofka.customer.client.CustomerClient;
import com.av.sofka.customer.model.Customer;
import com.av.sofka.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class CustomerController implements CustomerClient {

    @Autowired
    CustomerService service;

    @Override
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> getCustomer(Integer customerId) {
        return new ResponseEntity<>(service.getById(customerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> saveCustomer(Customer customer) {
        return new ResponseEntity<>(service.save(customer), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Integer customerId, Customer updateCustomerRequest) {
        return new ResponseEntity<>(service.updateCustomer(updateCustomerRequest, customerId), HttpStatus.OK);
    }

}
