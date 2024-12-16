package com.av.sofka.accounting.dao.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class ApiError {

    private final HttpStatus status;
    private final String message;
    private final ZonedDateTime timeStamp;
    private List<String> errors;

}
