package com.av.sofka.accounting.dao.rest.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private Integer customerId;
    private Boolean status;

}
