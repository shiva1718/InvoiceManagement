package com.shiva.invoicemanagement.entities;

import com.shiva.invoicemanagement.dto.InvoiceItemDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private double tax;
    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public InvoiceItem() {

    }

    public InvoiceItem(InvoiceItemDTO item, Invoice invoice) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.tax = item.getTax();
//        this.totalAmount = price * quantity;
        this.totalAmount = item.getTotalAmount();
        this.invoice = invoice;

    }

}
