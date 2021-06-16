package com.assignment.credorax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Objects;

import com.assignment.credorax.exception.InvalidExpDateException;
import com.assignment.credorax.exception.InvalidPanException;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "card")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(nullable = false)
    @NotBlank(message = "PAN is required")
    private String pan;

    @Column(nullable = false)
    @NotBlank(message = "Expiry is required")
    private String expiry;

    @JsonIgnore
    @Transient
    private String cvv;

    @OneToOne(mappedBy="card")
    private Payment payment;

    public Card() {
    }

    public Card(Long cardId, String pan, String expiry, String cvv) {
        this.cardId = cardId;
        this.pan = pan;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getPan() {
        String result = EncryptBase64.decode(pan);
        if(result == null || result.length() != 16)
            return "";

        return "************" + result.substring(12);
    }

    public void setPan(String pan) {
        try {
            double d = Double.parseDouble(pan);
        } catch (NumberFormatException nfe) {
            throw new InvalidPanException();
        }
        if(pan.isEmpty() || pan.length() != 16)
            throw new InvalidPanException();
        this.pan = EncryptBase64.encrypt(pan);
    }

    public String getExpiry() {
        return "****";
    }

    public void setExpiry(String expiry) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("MMyy");
            Date expiryDate = dateFormat.parse(expiry);
            Date currentDate = new Date();
            if(currentDate.after(expiryDate)) {
                throw new InvalidExpDateException("Invalid expiry date");
            }
        } catch (Exception ex) {
            throw new InvalidExpDateException("Invalid expiry date", ex);
        }

        this.expiry = EncryptBase64.encrypt(expiry);
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return pan.equals(card.pan) && expiry.equals(card.expiry) && cvv.equals(card.cvv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pan, expiry, cvv);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", pan='" + pan + '\'' +
                ", expiry='" + expiry + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
