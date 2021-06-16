package com.assignment.credorax.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;

import java.util.Objects;
import java.util.Set;

@Entity
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long invoice;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Cardholder cardholder;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Card card;

    public Payment() {
    }

    public Payment(Double amount, Currency currency, Cardholder cardholder, Card card) {
        this.amount = amount;
        this.currency = currency;
        this.cardholder = cardholder;
        this.card = card;
    }

    public Long getInvoice() {
        return invoice;
    }

    public void setInvoice(Long invoice) {
        this.invoice = invoice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Cardholder getCardholder() {
        return cardholder;
    }

    public void setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return currency == payment.currency && Objects.equals(cardholder, payment.cardholder) && Objects.equals(card, payment.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, cardholder, card);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "invoice=" + invoice +
                ", amount=" + amount +
                ", currency=" + currency +
                ", cardholder=" + cardholder +
                ", card=" + card +
                '}';
    }
}
