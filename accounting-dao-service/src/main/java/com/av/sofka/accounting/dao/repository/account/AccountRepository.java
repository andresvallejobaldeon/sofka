package com.av.sofka.accounting.dao.repository.account;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findByCustomer(Customer customer);

}