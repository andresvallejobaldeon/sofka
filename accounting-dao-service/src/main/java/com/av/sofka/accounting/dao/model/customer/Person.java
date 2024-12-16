package com.av.sofka.accounting.dao.model.customer;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "IDENTIFICATION")
    private String identification;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

}
