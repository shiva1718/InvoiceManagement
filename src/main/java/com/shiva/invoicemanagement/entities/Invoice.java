package com.shiva.invoicemanagement.entities;

import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.dto.InvoiceItemDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

@Entity
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> items;

    public Invoice(InvoiceDTO invoice, Customer customer) {
        this.date = Date.valueOf(invoice.getDate());
        this.customer = customer;
        items = new ArrayList<>();

    }

    public Invoice() {
        items = new ArrayList<>();
    }

    public void addItem(InvoiceItem invoiceItem) {
        items.add(invoiceItem);
        totalAmount += invoiceItem.getTotalAmount();
    }

}
