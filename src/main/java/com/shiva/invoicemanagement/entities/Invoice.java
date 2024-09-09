package com.shiva.invoicemanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.dto.InvoiceItemDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> items;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Payment> payments;

    public Invoice(InvoiceDTO invoice, Customer customer) {
        this.date = Date.valueOf(invoice.getDate());
        this.customer = customer;
        items = new ArrayList<>();
        payments = new ArrayList<>();
    }

    public Invoice() {
        items = new ArrayList<>();
        payments = new ArrayList<>();
    }

    public void addItem(InvoiceItem invoiceItem) {
        items.add(invoiceItem);
        totalAmount += invoiceItem.getTotalAmount();
    }

    public void makePayment(Payment newPayment) {
        payments.add(newPayment);
    }

    public double getTotalPaid() {
        return payments.stream().mapToDouble(Payment::getAmountPaid).sum();
    }
}
