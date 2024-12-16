package com.av.sofka.accounting.dao.service.account;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.account.Movement;

import java.util.Date;
import java.util.List;

public interface MovementService {

    void save(Movement movement);
    List<Movement> getAll();
    Movement getById (Integer movementId);
    void delete (Integer movementId);
    List<Movement> getAllAccountMovements (Account account);
    List<Movement> getCustomerMovementsByDates(Integer customerId, Date initDate, Date finalDate);

}
