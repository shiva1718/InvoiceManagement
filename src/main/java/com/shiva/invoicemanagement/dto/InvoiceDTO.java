package com.shiva.invoicemanagement.dto;

import com.shiva.invoicemanagement.entities.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InvoiceDTO {
    @Setter
    private Long id;

    private String date;

    @Setter
    private double totalAmount;

    @Setter
    private Long customerId;
    private String customerName;

    private List<InvoiceItemDTO> items;
    private List<PaymentDTO> payments;

    public InvoiceDTO() {
        items = new ArrayList<>();
        payments = new ArrayList<>();
    }

    public InvoiceDTO(Invoice invoice) {
        this.id = invoice.getId();
        this.date = invoice.getDate().toString();
        this.totalAmount = invoice.getTotalAmount();
        this.customerId = invoice.getCustomer().getId();
        items = new ArrayList<>();
        invoice.getItems().forEach(item -> items.add(new InvoiceItemDTO(item)));
        payments = new ArrayList<>();
        invoice.getPayments().forEach(payment -> payments.add(new PaymentDTO(payment)));
        this.customerName = invoice.getCustomer().getName();
    }

}
