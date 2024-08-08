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
    private String itemName;
    private double price;
    private int quantity;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public InvoiceItem() {

    }

    public InvoiceItem(InvoiceItemDTO item, Invoice invoice) {
        this.itemName = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.totalAmount = price * quantity;
        this.invoice = invoice;

    }
}
