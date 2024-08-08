package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.dto.PaymentDTO;
import com.shiva.invoicemanagement.entities.Invoice;
import com.shiva.invoicemanagement.entities.Payment;
import com.shiva.invoicemanagement.repo.InvoiceRepository;
import com.shiva.invoicemanagement.repo.PaymentRepository;
import exception.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    public PaymentDTO addPayment(PaymentDTO payment) {
        Long invoiceId = payment.getInvoiceId();
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isEmpty()) {
            throw new InvoiceNotFoundException("Invoice not found");
        }
        Payment newPayment = new Payment(payment, invoice.get());
        invoice.get().makePayment(newPayment);
        paymentRepository.save(newPayment);
        payment.setId(newPayment.getId());
        return payment;
    }
}
