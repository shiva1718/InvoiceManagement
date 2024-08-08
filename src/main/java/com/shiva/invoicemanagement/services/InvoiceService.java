package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.dto.InvoiceItemDTO;
import com.shiva.invoicemanagement.entities.InvoiceItem;
import com.shiva.invoicemanagement.repo.CustomerRepository;
import com.shiva.invoicemanagement.repo.InvoiceRepository;
import com.shiva.invoicemanagement.entities.Customer;
import com.shiva.invoicemanagement.entities.Invoice;
import exception.CustomerNotFoundException;
import exception.InvoiceNotFoundException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
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

    public InvoiceDTO addInvoice(InvoiceDTO invoice) {
        Long customerId = invoice.getCustomerId();
        if (customerRepository.findById(customerId).isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }
        Invoice newInvoice = new Invoice(invoice, customerRepository.findById(customerId).get());
        List<InvoiceItemDTO> items = invoice.getItems();
        items.forEach(item -> {
            newInvoice.addItem(new InvoiceItem(item, newInvoice));
        });
//        newInvoice.setDate(Date.valueOf(invoice.getDate()));
//        newInvoice.setTotalAmount(invoice.getTotalAmount());
//        newInvoice.setCustomer(customerRepository.findById(customerId).get());
        invoiceRepository.save(newInvoice);
        invoice.setId(newInvoice.getId());
        invoice.setTotalAmount(newInvoice.getTotalAmount());
        return invoice;
    }

    public List<InvoiceDTO> listAllInvoices() {
        List<Invoice> allInvoices = invoiceRepository.findAll();
        List<InvoiceDTO> invoiceDtos = new ArrayList<>(allInvoices.size());
        allInvoices.forEach(invoice -> {
            invoiceDtos.add(new InvoiceDTO(invoice));
        });
        return invoiceDtos;
    }

    public List<Invoice> listInvoicesByCustomer(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }

    public InvoiceDTO getInvoiceById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isEmpty()) {
            throw new InvoiceNotFoundException("Invoice not found");
        } else {
            return new InvoiceDTO(invoice.get());
        }
    }

    public void deleteInvoiceById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isEmpty()) {
            throw new InvoiceNotFoundException("Invoice not found");
        } else {
            invoiceRepository.deleteById(id);
        }
    }
}
