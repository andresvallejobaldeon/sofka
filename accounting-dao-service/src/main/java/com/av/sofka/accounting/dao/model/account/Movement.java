package com.av.sofka.accounting.dao.model.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "MOVEMENT")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVEMENT_ID")
    private Integer movementId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_NUMBER")
    private Account account;

    @Column(name = "MOVEMENT_DATE")
    private Date movementDate;

    @Column(name = "MOVEMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "INITIAL_BALANCE")
    private Double initialBalance;

}
