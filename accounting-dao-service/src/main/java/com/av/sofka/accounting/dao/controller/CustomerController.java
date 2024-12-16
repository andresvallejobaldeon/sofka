package com.av.sofka.accounting.dao.controller;

import com.av.sofka.accounting.dao.client.CustomerClient;
import com.av.sofka.accounting.dao.model.customer.Customer;
import com.av.sofka.accounting.dao.model.exception.NonExistingCustomerException;
import com.av.sofka.accounting.dao.rest.mapper.CustomerMapper;
import com.av.sofka.accounting.dao.rest.model.CustomerResponse;
import com.av.sofka.accounting.dao.rest.model.UpdateCustomerRequest;
import com.av.sofka.accounting.dao.service.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Validated
public class CustomerController implements CustomerClient {

    @Autowired
    CustomerService service;

    @Override
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        log.info("getCustomersCalled...");
        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerResponseList(service.getAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponse> getCustomer(Integer customerId) {
        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerResponse(service.getById(customerId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponse> saveCustomer(Customer customer) {
        log.info("saveCustomerRequest: - {}", customer);
        service.save(customer);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerResponse(customer), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(Integer customerId, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = service.getById(customerId);

        if (customer == null)
            throw new NonExistingCustomerException();

        CustomerMapper.INSTANCE.updateCustomer(updateCustomerRequest, customer);
        service.save(customer);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerResponse(customer), HttpStatus.OK);
    }

}
