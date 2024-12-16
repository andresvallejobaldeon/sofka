package com.av.sofka.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;

}
