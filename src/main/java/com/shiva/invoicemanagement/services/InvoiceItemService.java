package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.dto.InvoiceItemDTO;
import com.shiva.invoicemanagement.entities.Invoice;
import com.shiva.invoicemanagement.entities.InvoiceItem;
import com.shiva.invoicemanagement.repo.InvoiceItemRepository;
import com.shiva.invoicemanagement.repo.InvoiceRepository;
import exception.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceItemService {
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceItem addItemToInvoice(Long invoiceId, InvoiceItemDTO item) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isPresent()) {
            InvoiceItem newItem = new InvoiceItem(item, invoice.get());
            invoice.get().addItem(newItem);
//            item.setInvoice(invoice.get());
            return invoiceItemRepository.save(newItem);
        } else {
            throw new InvoiceNotFoundException("Invoice not found");
        }
    }
}
