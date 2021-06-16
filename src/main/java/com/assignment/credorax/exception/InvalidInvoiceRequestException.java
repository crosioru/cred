package com.assignment.credorax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid invoice")
public class InvalidInvoiceRequestException extends RuntimeException {
    public InvalidInvoiceRequestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public InvalidInvoiceRequestException(String errorMessage) {
        super(errorMessage);
    }
}
