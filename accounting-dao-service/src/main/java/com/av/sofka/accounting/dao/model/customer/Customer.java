package com.av.sofka.accounting.dao.model.customer;

import com.av.sofka.accounting.dao.model.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CUSTOMER")
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private Boolean status;

    @Getter(onMethod_ = @JsonIgnore)
    @OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
    private List<Account> accounts;

}
