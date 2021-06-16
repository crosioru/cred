package com.assignment.credorax;

import java.util.Optional;

import com.assignment.credorax.dto.CardDTO;
import com.assignment.credorax.dto.CardholderDTO;
import com.assignment.credorax.dto.PaymentDTO;
import com.assignment.credorax.model.Currency;
import com.assignment.credorax.model.Payment;
import com.assignment.credorax.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class PaymentTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void savingPaymentAndCheckingtableStructure() {
        CardDTO cardDTO = new CardDTO().setCvv("112").setExpiry("1224").setPan("1111222233334444");
        CardholderDTO cardholderDTO = new CardholderDTO().setName("John Doe").setEmail("nowhere@here.com");
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(12.22)
                .setCurrency(Currency.AUD)
                .setCard(cardDTO)
                .setCardholder(cardholderDTO);

        ModelMapper modelMapper = new ModelMapper();
        Payment payment = modelMapper.map(paymentDTO, Payment.class);

        Payment paymentResult = paymentRepository.save(payment);

        assertTrue(Optional.ofNullable(paymentResult).isPresent());
        assertEquals(paymentResult.getCard().getCvv(), "112");
    }

    @Test
    public void savingPaymentAndThrowingException() {
        CardDTO cardDTO = new CardDTO().setCvv("112").setExpiry("1212").setPan("1111222233334444");
        CardholderDTO cardholderDTO = new CardholderDTO().setName("John Doe").setEmail("nowhere@here.com");
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(12.22)
                .setCurrency(Currency.AUD)
                .setInvoice(123123L)
                .setCard(cardDTO)
                .setCardholder(cardholderDTO);

        ModelMapper modelMapper = new ModelMapper();

        Exception exception = assertThrows(MappingException.class, () -> {
             modelMapper.map(paymentDTO, Payment.class);
        });
        String actualMessage = exception.getCause().getMessage();
        assertTrue(actualMessage.contains("Expiry"));
     }

    @Test
    public void retrievingPaymentAndCheckingExceptions() {
        Optional<Payment> optionalPayment = paymentRepository.findPaymentByInvoice(123123L);
        assertTrue(Optional.ofNullable(optionalPayment).isPresent());
    }
}
