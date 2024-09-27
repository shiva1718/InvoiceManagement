package com.shiva.invoicemanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shiva.invoicemanagement.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    private String phone;
    private double balance;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Invoice> invoices;

    public Customer() {
    }

//    public Customer(CustomerDTO customer) {
//        this.name = customer.getName();
//        this.email = customer.getEmail();
//        this.address = customer.getAddress();
//        this.phone = customer.getPhone();
//        customer.getInvoices().forEach(invoiceDTO -> invoices.add(new Invoice(invoiceDTO, this)));
//    }

    public void update(CustomerDTO customer) {
        // update only fields which are not null
        if (customer.getName() != null) {
            this.name = customer.getName();
        }
        if(customer.getPhone() != null) {
            this.phone = customer.getPhone();
        }
        if (customer.getEmail() != null) {
            this.email = customer.getEmail();
        }
        if (customer.getAddress() != null) {
            this.address = new Address(customer.getAddress());
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

    public void updateInvoice(Invoice invoice) {
        for(int i = 0; i < invoices.size(); i++) {
			if(Objects.equals(invoices.get(i).getId(), invoice.getId())) {
                invoices.set(i, invoice);
                updateBalance();
                return;
			}
        }
    }


}
