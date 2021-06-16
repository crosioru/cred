package com.assignment.credorax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

import java.util.Objects;

@Entity
@Table(name = "cardholder")
public class Cardholder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardholder_id")
    private Long cardholderId;

    private String name;

    private String email;

    @OneToOne(mappedBy="cardholder")
    private Payment payment;

    public Cardholder() {
    }

    public Cardholder(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getCardholderId() {
        return cardholderId;
    }

    public void setCardholderId(Long cardholderId) {
        this.cardholderId = cardholderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cardholder)) return false;
        Cardholder that = (Cardholder) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return "Cardholder{" +
                "cardholderId=" + cardholderId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
