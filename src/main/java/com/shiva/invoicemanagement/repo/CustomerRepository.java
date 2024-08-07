package com.shiva.invoicemanagement.repo;

import com.shiva.invoicemanagement.entities.Customer;
import com.shiva.invoicemanagement.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}