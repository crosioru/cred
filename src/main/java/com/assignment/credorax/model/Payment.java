package com.assignment.credorax.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Positive;

import java.io.Serializable;

import java.util.Objects;

import com.assignment.credorax.exception.InvalidAmountRequestException;

@Entity
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long paymentId;

    @Column(name = "invoice")
    private Long invoice;

    @Positive(message = "Amount is required and positive integer")
    private Integer amount;

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

    public Payment(Long paymentId, Long invoice, Integer amount, Currency currency, Cardholder cardholder, Card card) {
        this.paymentId = paymentId;
        this.invoice = invoice;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        if(amount.intValue() <= 0)
            throw new InvalidAmountRequestException();
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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long id) {
        this.paymentId = id;
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
