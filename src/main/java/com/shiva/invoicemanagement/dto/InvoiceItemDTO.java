package com.shiva.invoicemanagement.dto;

import com.shiva.invoicemanagement.entities.InvoiceItem;

public class InvoiceItemDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private double totalAmount;
    private Long invoiceId;

    public InvoiceItemDTO() {
    }

    public InvoiceItemDTO(InvoiceItem item) {
        this.id = item.getId();
        this.name = item.getItemName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.totalAmount = item.getTotalAmount();
        this.invoiceId = item.getInvoice().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }
}
