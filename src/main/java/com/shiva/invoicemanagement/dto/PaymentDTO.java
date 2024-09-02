package com.shiva.invoicemanagement.dto;

import com.shiva.invoicemanagement.entities.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PaymentDTO {
    @Setter
    private Long id;
    private String date;
    private double amountPaid;
    private Long invoiceId;
    private String paymentType;

    public PaymentDTO() {
    }

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.date = payment.getDate().toString();
        this.amountPaid = payment.getAmountPaid();
        this.invoiceId = payment.getInvoice().getId();
        this.paymentType = payment.getPaymentType().name();
    }
}
