package com.assignment.credorax.dto;

import java.io.Serializable;

import java.util.Objects;

import com.assignment.credorax.exception.CardholderException;
import com.assignment.credorax.model.Cardholder;
import com.assignment.credorax.model.Currency;
import com.assignment.credorax.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentDTO implements Serializable {
    private Long paymentId;
    private Long invoice;
    private Double amount;
    private Currency currency;
    private CardholderDTO cardholder;
    private CardDTO card;

    public PaymentDTO() {
    }

    public Long getInvoice() {
        return invoice;
    }

    public PaymentDTO setInvoice(Long invoice) {
        this.invoice = invoice;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public PaymentDTO setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public PaymentDTO setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public CardholderDTO getCardholder() {
        return cardholder;
    }

    public PaymentDTO setCardholder(CardholderDTO cardholder) {
        this.cardholder = cardholder;
        return this;
    }

    public CardDTO getCard() {
        return card;
    }

    public PaymentDTO setCard(CardDTO card) {
        this.card = card;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDTO)) return false;
        PaymentDTO that = (PaymentDTO) o;
        return Objects.equals(invoice, that.invoice) && Objects.equals(amount, that.amount) && currency == that.currency && Objects.equals(cardholder, that.cardholder) && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice, amount, currency, cardholder, card);
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "invoice=" + invoice +
                ", amount=" + amount +
                ", currency=" + currency +
                ", cardholder=" + cardholder +
                ", card=" + card +
                '}';
    }

    public static PaymentDTO convertToDto(Payment payment) {
        ModelMapper modelMapper = new ModelMapper();
        Cardholder cardholder = payment.getCardholder();
        if(cardholder == null)
            throw new CardholderException();
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public static Payment convertToEntity(PaymentDTO paymentDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paymentDto, Payment.class);
    }
}
