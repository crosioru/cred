package com.assignment.credorax.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public InvoiceNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public InvoiceNotFoundException() {
        super();
    }
}
