package com.assignment.credorax.service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.assignment.credorax.dto.PaymentDTO;
import com.assignment.credorax.exception.InvoiceNotFoundException;
import com.assignment.credorax.model.Payment;
import com.assignment.credorax.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaymentService {

    private PaymentRepository paymentRepo;

    public PaymentService(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    public List<PaymentDTO> findAllInvoices() {
        List<Payment> payments = paymentRepo.findAll();
        return payments.stream()
                .map(PaymentDTO::convertToDto)
                .collect(Collectors.toList());
    }

    public Payment createPayment(PaymentDTO paymentDTO) {
        Payment payment = PaymentDTO.convertToEntity(paymentDTO);
        return paymentRepo.save(payment);
    }

    public PaymentDTO getInvoice( Long invoice) {
        Optional<Payment> optionalPayment = paymentRepo.findPaymentByInvoice(invoice);
        return optionalPayment.map(PaymentDTO::convertToDto).orElseThrow(() -> new InvoiceNotFoundException());
    }
}
