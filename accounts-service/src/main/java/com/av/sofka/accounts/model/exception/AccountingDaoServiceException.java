package com.av.sofka.accounts.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AccountingDaoServiceException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

}
