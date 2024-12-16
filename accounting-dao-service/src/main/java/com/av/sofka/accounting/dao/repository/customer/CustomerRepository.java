package com.av.sofka.accounting.dao.repository.customer;

import com.av.sofka.accounting.dao.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
