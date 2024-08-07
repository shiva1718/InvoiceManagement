package com.shiva.invoicemanagement.dto;

public class InvoiceDTO {
    private Long id;
    private String date;
    private double totalAmount;
    private Long customerId;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Long id, String date, double totalAmount, Long customerId) {
        this.id = id;
        this.date = date;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
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
}
