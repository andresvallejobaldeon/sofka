package com.av.sofka.accounting.dao.service.account.impl;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.account.Movement;
import com.av.sofka.accounting.dao.repository.account.MovementRepository;
import com.av.sofka.accounting.dao.service.account.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    MovementRepository repository;

    @Override
    public void save(Movement movement) {
        repository.save(movement);
    }

    @Override
    public List<Movement> getAll() {
        return repository.findAll();
    }

    @Override
    public Movement getById(Integer movementId) {
        Optional<Movement> optional = repository.findById(movementId);
        return optional.orElse(null);
    }

    @Override
    public void delete(Integer movementId) {
        if (repository.existsById(movementId))
            repository.deleteById(movementId);
    }

    @Override
    public List<Movement> getAllAccountMovements(Account account) {
        return repository.findByAccount(account);
    }

    @Override
    public List<Movement> getCustomerMovementsByDates(Integer customerId, Date initDate, Date finalDate) {
        return repository.findByCustomerAndDates(customerId, initDate, finalDate);
    }

}
