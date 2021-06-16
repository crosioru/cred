package com.assignment.credorax.repository;

import java.util.Optional;

import com.assignment.credorax.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findPaymentByInvoice(Long invoice);
}
