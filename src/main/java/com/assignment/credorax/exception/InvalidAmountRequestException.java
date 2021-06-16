package com.assignment.credorax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid amount")
public class InvalidAmountRequestException extends RuntimeException {
    public InvalidAmountRequestException() {
        super();
    }
}
