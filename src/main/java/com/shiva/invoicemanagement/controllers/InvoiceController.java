package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.entities.Invoice;
import com.shiva.invoicemanagement.services.InvoiceService;
import exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Invoice> addInvoice(@PathVariable Long customerId, @RequestBody InvoiceDTO invoice) {
        if (invoice == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return ResponseEntity.ok(invoiceService.addInvoice(customerId, invoice));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> listAllInvoices() {
        return ResponseEntity.ok(invoiceService.listAllInvoices());
    }

//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<Invoice>> listInvoicesByCustomer(@PathVariable Long customerId) {
//        return ResponseEntity.ok(invoiceService.listInvoicesByCustomer(customerId));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
