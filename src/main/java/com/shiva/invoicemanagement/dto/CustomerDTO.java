package com.shiva.invoicemanagement.dto;

import com.shiva.invoicemanagement.entities.Address;
import com.shiva.invoicemanagement.entities.Customer;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private double balance;
    private List<InvoiceDTO> invoices = new ArrayList<>();

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.address = customer.getAddress().toString();
        this.phone = customer.getPhone();
        this.balance = customer.getBalance();
        customer.getInvoices().forEach(invoice -> invoices.add(new InvoiceDTO(invoice)));

    }
}
