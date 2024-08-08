package com.shiva.invoicemanagement.dto;

import com.shiva.invoicemanagement.entities.Invoice;
import com.shiva.invoicemanagement.entities.InvoiceItem;
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
    private List<InvoiceItemDTO> items;

    public InvoiceDTO() {
        items = new ArrayList<>();
    }

    public InvoiceDTO(Long id, String date, double totalAmount, Long customerId) {
        this.id = id;
        this.date = date;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
        items = new ArrayList<>();
    }

    public InvoiceDTO(Invoice invoice) {
        this.id = invoice.getId();
        this.date = invoice.getDate().toString();
        this.totalAmount = invoice.getTotalAmount();
        this.customerId = invoice.getCustomer().getId();
        items = new ArrayList<>();
        invoice.getItems().forEach(item -> items.add(new InvoiceItemDTO(item)));
    }

}
