package com.shiva.invoicemanagement.dto;

import lombok.Getter;

import com.shiva.invoicemanagement.entities.InvoiceItem;

@Getter
public class InvoiceItemDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private double tax;
    private double totalAmount;
    private Long invoiceId;

    public InvoiceItemDTO() {
    }

    public InvoiceItemDTO(InvoiceItem item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.tax = item.getTax();
        this.totalAmount = item.getTotalAmount();
        this.invoiceId = item.getInvoice().getId();
    }
}
