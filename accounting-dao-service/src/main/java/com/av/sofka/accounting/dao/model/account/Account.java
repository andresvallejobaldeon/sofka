package com.av.sofka.accounting.dao.model.account;

import com.av.sofka.accounting.dao.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ACCOUNT")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "INITIAL_BALANCE")
    private Double initialBalance;

    @Column(name = "BALANCE")
    private Double balance = initialBalance;

    @Column(name = "STATUS")
    private Boolean status;

    @Getter(onMethod = @__( @JsonIgnore))
    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    private List<Movement> movements;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

}
