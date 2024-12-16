package com.av.sofka.accounting.dao.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class NonExistingAccountException extends RuntimeException {

    private final String message = "Cuenta no existe";

}
