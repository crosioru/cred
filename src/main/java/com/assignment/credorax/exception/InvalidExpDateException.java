package com.assignment.credorax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid expiry date")
public class InvalidExpDateException extends RuntimeException {
    public InvalidExpDateException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public InvalidExpDateException(String errorMessage) {
        super(errorMessage);
    }
}
