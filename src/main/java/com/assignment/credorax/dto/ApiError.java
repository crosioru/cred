package com.assignment.credorax.dto;

public class ApiError {

    private Boolean approved;
    private String error;

    public ApiError(final boolean approved, final String developerMessage) {
        this.approved = approved;
        this.error = developerMessage;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getError() {
        return error;
    }

    public void setDeveloperMessage(final String developerMessage) {
        this.error = developerMessage;
    }

    //

    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ApiError [status=")
                .append(", message=")
                .append(", developerMessage=")
                .append(error)
                .append("]");
        return builder.toString();
    }
}
