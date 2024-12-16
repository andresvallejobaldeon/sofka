package com.av.sofka.accounting.dao.exceptionhandling;

import com.av.sofka.accounting.dao.model.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;
import java.util.Collections;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ApiError> handleInsufficientFundsException(final InsufficientFundsException exception, final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), ZonedDateTime.now(), Collections.singletonList(exception.getMessage()));
        log.error(exception.getMessage());
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(InactiveAccountException.class)
    public ResponseEntity<ApiError> handleInactiveAccountException(final InactiveAccountException exception, final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), ZonedDateTime.now(), Collections.singletonList(exception.getMessage()));
        log.error(exception.getMessage());
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(NonExistingAccountException.class)
    public ResponseEntity<ApiError> handleNonExistingAccountException(final NonExistingAccountException exception, final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), ZonedDateTime.now(), Collections.singletonList(exception.getMessage()));
        log.error(exception.getMessage());
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(NonExistingCustomerException.class)
    public ResponseEntity<ApiError> handleNonExistingCustomerException(final NonExistingCustomerException exception, final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), ZonedDateTime.now(), Collections.singletonList(exception.getMessage()));
        log.error(exception.getMessage());
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiError> handleCustomerNotFoundException(final CustomerNotFoundException exception, final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), ZonedDateTime.now(), Collections.singletonList(exception.getMessage()));
        log.error(exception.getMessage());
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
