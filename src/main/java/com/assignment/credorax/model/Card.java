package com.assignment.credorax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

import java.util.Objects;

@Entity
@Table(name = "card")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    private String pan;
    private String expiry;
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
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
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
