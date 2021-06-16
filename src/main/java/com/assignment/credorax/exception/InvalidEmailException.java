package com.assignment.credorax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid email value")
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super();
    }
}
