package com.assignment.credorax.controller;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.assignment.credorax.dto.PaymentDTO;
import com.assignment.credorax.exception.ApprovedResponse;
import com.assignment.credorax.exception.InvoiceNotFoundException;
import com.assignment.credorax.model.Payment;
import com.assignment.credorax.repository.CardRepository;
import com.assignment.credorax.repository.CardholderRepository;
import com.assignment.credorax.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credorax")
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MappingJacksonValue createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        Payment payment = PaymentDTO.convertToEntity(paymentDTO);
        payment.setPaymentId(123L);
        paymentRepo.save(payment);

        ApprovedResponse approvedResponse = new ApprovedResponse().setApproved(true);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("approved");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ApprovedFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(approvedResponse);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/transactions/{invoice}")
    public PaymentDTO getInvoice(@PathVariable(name = "invoice") Long invoice) {
        Optional<Payment> optionalPayment = paymentRepo.findPaymentByInvoice(invoice);
        return optionalPayment.map(PaymentDTO::convertToDto).orElseThrow(() -> new InvoiceNotFoundException());
    }
}
