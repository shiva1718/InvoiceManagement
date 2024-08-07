package com.shiva.invoicemanagement.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> invoices;

    public void update(Customer customer) {
        // update only fields which are not null
        if (customer.getName() != null) {
            this.name = customer.getName();
        }
        if (customer.getEmail() != null) {
            this.email = customer.getEmail();
        }
        if (customer.getAddress() != null) {
            this.address = customer.getAddress();
        }
    }
}
