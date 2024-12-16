package com.av.sofka.accounting.dao.client;

import com.av.sofka.accounting.dao.model.customer.Customer;
import com.av.sofka.accounting.dao.rest.model.CustomerResponse;
import com.av.sofka.accounting.dao.rest.model.UpdateCustomerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
public interface CustomerClient {

    @GetMapping("")
    ResponseEntity<List<CustomerResponse>> getCustomers();

    @GetMapping("/{customerId}")
    ResponseEntity<CustomerResponse> getCustomer(@PathVariable Integer customerId);

    @PostMapping("")
    ResponseEntity<CustomerResponse> saveCustomer(@RequestBody Customer customer);

    @PatchMapping("/{customerId}")
    ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Integer customerId, @RequestBody UpdateCustomerRequest updateCustomerRequest);

}
