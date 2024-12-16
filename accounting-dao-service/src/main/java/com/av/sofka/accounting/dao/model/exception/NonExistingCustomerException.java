package com.av.sofka.accounting.dao.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class NonExistingCustomerException extends RuntimeException {

    private final String message = "Cliente no existe";

}