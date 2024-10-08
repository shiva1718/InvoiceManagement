package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.dto.InvoiceItemDTO;
import com.shiva.invoicemanagement.entities.Customer;
import com.shiva.invoicemanagement.entities.InvoiceItem;
import com.shiva.invoicemanagement.repo.CustomerRepository;
import com.shiva.invoicemanagement.repo.InvoiceItemRepository;
import com.shiva.invoicemanagement.repo.InvoiceRepository;
import com.shiva.invoicemanagement.entities.Invoice;
import com.shiva.invoicemanagement.exception.CustomerNotFoundException;
import com.shiva.invoicemanagement.exception.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

//    @Autowired
//    private InvoiceItemRepository invoiceItemRepository;

    public InvoiceDTO addInvoice(InvoiceDTO invoice) {
        Long customerId = invoice.getCustomerId();
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }
        Customer customer = optionalCustomer.get();
        Invoice newInvoice = new Invoice(invoice, customer);
//        List<InvoiceItemDTO> items = invoice.getItems();
//        items.forEach(item -> {
//            newInvoice.addItem(new InvoiceItem(item, newInvoice));
//        });
//        newInvoice.setDate(Date.valueOf(invoice.getDate()));
//        newInvoice.setTotalAmount(invoice.getTotalAmount());
//        newInvoice.setCustomer(customerRepository.findById(customerId).get());
        invoice.setId(newInvoice.getId());
//        invoice.setTotalAmount(newInvoice.getTotalAmount());
        customer.addBalance(invoice.getTotalAmount());
        invoiceRepository.save(newInvoice);
        return invoice;
    }

    public InvoiceDTO editInvoiceById(Long id, InvoiceDTO updatedInvoice) {
        Optional<Invoice> byId = invoiceRepository.findById(id);
        if(byId.isEmpty()) {
            throw new InvoiceNotFoundException("Invoiced ID not found");
        }
        Invoice newInvoice = byId.get();
        for(InvoiceItemDTO invItem: updatedInvoice.getItems()) {
            invoiceItemRepository.deleteById(invItem.getId());
        }
        Customer updatedCustomer = customerRepository.findById(newInvoice.getCustomer().getId()).get();
        newInvoice.updateInvoice(updatedInvoice, updatedCustomer);
        return new InvoiceDTO(invoiceRepository.save(newInvoice));
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
            invoice.get().getCustomer().subtractBalance(invoice.get().getTotalAmount());
            invoiceRepository.deleteById(id);
        }
    }
}
