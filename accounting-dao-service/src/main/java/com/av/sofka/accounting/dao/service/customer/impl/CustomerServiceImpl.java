package com.av.sofka.accounting.dao.service.customer.impl;

import com.av.sofka.accounting.dao.model.customer.Customer;
import com.av.sofka.accounting.dao.model.exception.CustomerNotFoundException;
import com.av.sofka.accounting.dao.repository.customer.CustomerRepository;
import com.av.sofka.accounting.dao.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public void save(Customer customer) {
        repository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public Customer getById(Integer customerId) {
        Optional<Customer> optional = repository.findById(customerId);
        return optional.orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public void delete(Integer customerId) {
        if (repository.existsById(customerId))
            repository.deleteById(customerId);
    }

}
