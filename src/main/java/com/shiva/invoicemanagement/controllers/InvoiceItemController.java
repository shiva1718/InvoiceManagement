package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.entities.InvoiceItem;
import com.shiva.invoicemanagement.services.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class InvoiceItemController {
    @Autowired
    private InvoiceItemService invoiceItemService;

    @PostMapping("/{invoiceId}")
    public ResponseEntity<InvoiceItem> addItemToInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceItem item) {
        return ResponseEntity.ok(invoiceItemService.addItemToInvoice(invoiceId, item));
    }
}
