package com.av.sofka.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCustomer {

    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private Integer customerId;
    private String status;

}
