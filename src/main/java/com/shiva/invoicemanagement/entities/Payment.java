package com.shiva.invoicemanagement.entities;

import com.shiva.invoicemanagement.dto.PaymentDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double amountPaid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    private PaymentType paymentType;

    public Payment() {
    }

    public Payment(PaymentDTO payment, Invoice invoice) {
        this.date = Date.valueOf(payment.getDate());
        this.amountPaid = payment.getAmountPaid();
        this.invoice = invoice;
        this.paymentType = PaymentType.valueOf(payment.getPaymentType().toUpperCase());
    }
}
