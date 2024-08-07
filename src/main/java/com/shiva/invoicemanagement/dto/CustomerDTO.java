package com.shiva.invoicemanagement.dto;

import java.util.List;

public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private List<InvoiceDTO> invoices;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String email, String address, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public CustomerDTO(Long id, String name, String email, String address, String phone, List<InvoiceDTO> invoices) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.invoices = invoices;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
