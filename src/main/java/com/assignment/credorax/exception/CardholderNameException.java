package com.assignment.credorax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing cardholder name")
public class CardholderNameException extends RuntimeException {
    public CardholderNameException(String errorMessage) {
        super(errorMessage);
    }
    public CardholderNameException() {
        super();
    }
}