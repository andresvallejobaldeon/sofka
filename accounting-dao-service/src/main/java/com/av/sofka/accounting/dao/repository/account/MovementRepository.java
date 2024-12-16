package com.av.sofka.accounting.dao.repository.account;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.model.account.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {

    List<Movement> findByAccount(Account account);

    @Query(value = "select m.* from movement m " +
            "inner join account a on m.account_number = a.account_number " +
            "inner join customer c on a.customer_id = c.customer_id " +
            "where c.customer_id = :customer_id and m.movement_date between :init_date and :final_date " +
            "order by m.movement_date, m.movement_id", nativeQuery = true)
    List<Movement> findByCustomerAndDates(@Param("customer_id") Integer customerId, @Param("init_date") Date initDate,
                                          @Param("final_date") Date finalDate);

}
