package com.assignment.credorax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Missing cardholder details")
public class CardholderException extends RuntimeException {
    public CardholderException(String errorMessage) {
        super(errorMessage);
    }
    public CardholderException() {
        super();
    }
}
