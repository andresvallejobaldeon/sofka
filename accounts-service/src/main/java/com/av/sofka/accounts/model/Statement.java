package com.av.sofka.accounts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statement {

    @JsonProperty("Fecha")
    private Date movementDate;

    @JsonProperty("Cliente")
    private String customerName;

    @JsonProperty("Numero Cuenta")
    private String accountNumber;

    @JsonProperty("Tipo")
    private String accountType;

    @JsonProperty("Saldo Inicial")
    private Double initialBalance;

    @JsonProperty("Estado")
    private Boolean status;

    @JsonProperty("Movimiento")
    private Double movementAmount;

    @JsonProperty("Saldo Disponible")
    private Double balance;

}
