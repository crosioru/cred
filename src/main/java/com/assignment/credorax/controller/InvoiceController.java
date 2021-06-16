package com.assignment.credorax.controller;

import javax.validation.Valid;

import java.util.List;

import com.assignment.credorax.dto.PaymentDTO;
import com.assignment.credorax.exception.ApprovedResponse;
import com.assignment.credorax.repository.CardRepository;
import com.assignment.credorax.repository.CardholderRepository;
import com.assignment.credorax.service.PaymentService;
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

    private PaymentService paymentService;

    public InvoiceController(ModelMapper modelMapper, CardholderRepository cardholderRepository,
                             CardRepository cardRepository, PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public List<PaymentDTO> getInvoices() {
        return paymentService.findAllInvoices();
    }

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MappingJacksonValue createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        paymentService.createPayment(paymentDTO);
        ApprovedResponse approvedResponse = new ApprovedResponse().setApproved(true);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("approved");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ApprovedFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(approvedResponse);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/transactions/{invoice}")
    public PaymentDTO getInvoice(@PathVariable(name = "invoice") Long invoice) {
        return paymentService.getInvoice(invoice);
    }
}
