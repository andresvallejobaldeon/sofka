package com.av.sofka.accounts.exceptionhandling;

import com.av.sofka.accounts.model.exception.AccountingDaoServiceException;
import com.av.sofka.accounts.model.exception.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;
import java.util.Collections;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(AccountingDaoServiceException.class)
    public ResponseEntity<ApiError> handleAccountingDaoServiceException(final AccountingDaoServiceException exception, final WebRequest request) {
        final ApiError apiError = new ApiError(exception.getHttpStatus(), exception.getLocalizedMessage(), ZonedDateTime.now(), Collections.singletonList(exception.getMessage()));
        log.error(exception.getMessage());
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
