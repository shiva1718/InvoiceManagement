package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.services.InvoiceService;
import com.shiva.invoicemanagement.exception.CustomerNotFoundException;
import com.shiva.invoicemanagement.exception.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/add/")
    public ResponseEntity<?> addInvoice(@RequestBody InvoiceDTO invoice) {
        if (invoice == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
//        Long customerId = invoice.getCustomerId();
        try {
            return ResponseEntity.ok(invoiceService.addInvoice(invoice));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> listAllInvoices() {
        return ResponseEntity.ok(invoiceService.listAllInvoices());
    }

//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<Invoice>> listInvoicesByCustomer(@PathVariable Long customerId) {
//        return ResponseEntity.ok(invoiceService.listInvoicesByCustomer(customerId));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(invoiceService.getInvoiceById(id));
        } catch (InvoiceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice ID not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoiceById(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoiceById(id);
            return ResponseEntity.ok("Invoice deleted successfully");
        } catch (InvoiceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice ID not found");
        }
    }
}
