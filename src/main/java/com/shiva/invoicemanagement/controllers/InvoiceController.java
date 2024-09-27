package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.services.InvoiceService;
import com.shiva.invoicemanagement.exception.CustomerNotFoundException;
import com.shiva.invoicemanagement.exception.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<?> addInvoice(@RequestBody InvoiceDTO invoice) {
        if (invoice == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return ResponseEntity.ok(invoiceService.addInvoice(invoice));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> listAllInvoices() {
        System.out.println("received invoice list req");
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

    @PutMapping("{id}")
    public ResponseEntity<?> editInvoiceById(@PathVariable Long id, @RequestBody InvoiceDTO invoice) {
        try {
            return ResponseEntity.ok(invoiceService.editInvoiceById(id, invoice));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while editing invoice");
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
