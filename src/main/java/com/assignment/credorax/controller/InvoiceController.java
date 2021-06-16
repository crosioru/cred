package com.assignment.credorax.controller;

import java.text.ParseException;

import java.util.List;
import java.util.stream.Collectors;

import com.assignment.credorax.dto.PaymentDTO;
import com.assignment.credorax.model.Payment;
import com.assignment.credorax.repository.CardRepository;
import com.assignment.credorax.repository.CardholderRepository;
import com.assignment.credorax.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    private ModelMapper modelMapper;
    private CardholderRepository cardholderRepo;
    private CardRepository cardRepo;
    private PaymentRepository paymentRepo;

    public InvoiceController(ModelMapper modelMapper, CardholderRepository cardholderRepository,
                             CardRepository cardRepository, PaymentRepository paymentRepository) {
        this.modelMapper = modelMapper;
        this.cardholderRepo = cardholderRepository;
        this.cardRepo = cardRepository;
        this.paymentRepo = paymentRepository;
    }

    @GetMapping("/payments")
    public List<PaymentDTO> getInvoices() {
        List<Payment> payments = paymentRepo.findAll();
        return payments.stream()
                .map(PaymentDTO::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/payments")
    public PaymentDTO createPayment(@Validated @RequestBody PaymentDTO paymentDTO) {
        Payment payment = PaymentDTO.convertToEntity(paymentDTO);
        Payment resPayment = paymentRepo.save(payment);
        return PaymentDTO.convertToDto(resPayment);
    }

//    private PaymentDTO convertToDto(Payment payment) {
//        PaymentDTO paymentDTO = modelMapper.map(payment, PaymentDTO.class);
//        return paymentDTO;
//    }
//
//    private Payment convertToEntity(PaymentDTO paymentDto) {
//        Payment payment = modelMapper.map(paymentDto, Payment.class);
//        return payment;
//    }
}
