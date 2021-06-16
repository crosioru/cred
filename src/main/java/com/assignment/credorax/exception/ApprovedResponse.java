package com.assignment.credorax.exception;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("ApprovedFilter")
public class ApprovedResponse implements Serializable {
    private boolean approved;
    private List<String> errors;

    public ApprovedResponse() {
        errors = new ArrayList<>();
    }

    public boolean isApproved() {
        return approved;
    }

    public ApprovedResponse setApproved(boolean approved) {
        if(approved)
            errors = null;
        this.approved = approved;
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public ApprovedResponse setErrors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public ApprovedResponse addError(String error) {
        this.errors.add(error);
        return this;
    }
}
