package com.assignment.credorax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid pan value")
public class InvalidPanException extends RuntimeException {
    public InvalidPanException() {
        super();
    }
}
