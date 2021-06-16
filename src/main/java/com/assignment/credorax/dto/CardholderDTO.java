package com.assignment.credorax.dto;

import java.io.Serializable;

import java.util.Objects;

public class CardholderDTO implements Serializable {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public CardholderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CardholderDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardholderDTO)) return false;
        CardholderDTO that = (CardholderDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return "CardholderDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
