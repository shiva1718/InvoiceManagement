package com.shiva.invoicemanagement.repo;

import com.shiva.invoicemanagement.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
