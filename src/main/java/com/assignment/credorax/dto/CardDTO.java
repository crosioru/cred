package com.assignment.credorax.dto;

import java.io.Serializable;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CardDTO implements Serializable {
    private String pan;
    private String expiry;

    @JsonIgnore
    private String cvv;

    public String getPan() {
        return pan;
    }

    public CardDTO setPan(String pan) {
        this.pan = pan;
        return this;
    }

    public String getExpiry() {
        return expiry;
    }

    public CardDTO setExpiry(String expiry) {
        this.expiry = expiry;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public CardDTO setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardDTO)) return false;
        CardDTO cardDTO = (CardDTO) o;
        return Objects.equals(pan, cardDTO.pan) && Objects.equals(expiry, cardDTO.expiry) && Objects.equals(cvv, cardDTO.cvv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pan, expiry, cvv);
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "pan='" + pan + '\'' +
                ", expiry='" + expiry + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
