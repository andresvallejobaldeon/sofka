package com.av.sofka.customer.client;

import com.av.sofka.customer.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
public interface CustomerClient {

    @GetMapping("")
    ResponseEntity<List<Customer>> getCustomers();

    @GetMapping("/{customerId}")
    ResponseEntity<Customer> getCustomer(@PathVariable Integer customerId);

    @PostMapping("")
    ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer);

    @PatchMapping("/{customerId}")
    ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer updateCustomerRequest);

}
