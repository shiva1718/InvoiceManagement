package com.shiva.invoicemanagement.dto;

import com.shiva.invoicemanagement.entities.Invoice;
import com.shiva.invoicemanagement.entities.InvoiceItem;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDTO {
    private Long id;
    private String date;
    private double totalAmount;
    private Long customerId;
    private List<InvoiceItemDTO> items = new ArrayList<>();

    public InvoiceDTO() {
    }

    public InvoiceDTO(Long id, String date, double totalAmount, Long customerId) {
        this.id = id;
        this.date = date;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
    }

    public InvoiceDTO(Invoice invoice) {
        this.id = invoice.getId();
        this.date = invoice.getDate().toString();
        this.totalAmount = invoice.getTotalAmount();
        this.customerId = invoice.getCustomer().getId();
        invoice.getItems().forEach(item -> items.add(new InvoiceItemDTO(item)));
    }

    public Long getId() {
        return id;
    }
    public String getDate() {
        return date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<InvoiceItemDTO> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
