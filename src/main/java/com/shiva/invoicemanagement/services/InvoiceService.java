package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.repo.CustomerRepository;
import com.shiva.invoicemanagement.repo.InvoiceRepository;
import com.shiva.invoicemanagement.entities.Customer;
import com.shiva.invoicemanagement.entities.Invoice;
import exception.CustomerNotFoundException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

//    @Autowired
//    private InvoiceItemRepository invoiceItemRepository;

    public Invoice addInvoice(Long customerId, InvoiceDTO invoice) {
        if (customerRepository.findById(customerId).isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }
        Invoice newInvoice = new Invoice();
        newInvoice.setDate(Date.valueOf(invoice.getDate()));
        newInvoice.setTotalAmount(invoice.getTotalAmount());
        newInvoice.setCustomer(customerRepository.findById(customerId).get());
        return invoiceRepository.save(newInvoice);
    }

    public List<Invoice> listAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> listInvoicesByCustomer(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }
}
