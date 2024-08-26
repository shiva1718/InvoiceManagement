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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    private String phone;
    private double balance;

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

    public void addBalance(double toAdd) {
        this.balance += toAdd;
    }

    public void subtractBalance(double toSubtract) {
        this.balance -= toSubtract;
    }

    public double updateBalance() {
        double totalAmount = invoices.stream().mapToDouble(Invoice::getTotalAmount).sum();
        double totalPaid = invoices.stream().mapToDouble(Invoice::getTotalPaid).sum();
        balance = totalAmount - totalPaid;
        return balance;
    }
}
