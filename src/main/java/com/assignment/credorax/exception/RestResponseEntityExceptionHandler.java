package com.assignment.credorax.exception;

import com.assignment.credorax.dto.ApiError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
       if(((InvalidFormatException) ex.getCause()).getTargetType().getName().contains("Currency")) {
           return handleExceptionInternal(ex,messages(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
       }
       return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { InvalidInvoiceRequestException.class })
    protected final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex,messages(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { InvalidPanException.class, InvalidEmailException.class, CardholderNameException.class,
            CardholderException.class, InvalidAmountRequestException.class, InvalidExpDateException.class, InvoiceNotFoundException.class})
    protected final ResponseEntity<Object> handleInvalidInputRequest(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, messages(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    protected final ApiError messages(final Exception ex) {
        final String devMessage = ExceptionUtils.getRootCauseMessage(ex).replace(": ", "");
        return new ApiError(false, devMessage);
    }
}
