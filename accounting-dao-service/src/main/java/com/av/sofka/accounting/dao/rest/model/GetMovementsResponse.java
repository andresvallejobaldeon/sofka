package com.av.sofka.accounting.dao.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetMovementsResponse {

    @JsonProperty("Fecha")
    private Date date;

    private String customerName;
    private String accountNumber;
    private String accountType;


}
