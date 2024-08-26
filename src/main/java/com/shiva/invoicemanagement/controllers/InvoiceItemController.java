package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.InvoiceItemDTO;
import com.shiva.invoicemanagement.entities.InvoiceItem;
import com.shiva.invoicemanagement.services.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
public class InvoiceItemController {
    @Autowired
    private InvoiceItemService invoiceItemService;

//    @PostMapping("/add/")
    @PostMapping
    public ResponseEntity<?> addItemToInvoice(@RequestBody InvoiceItemDTO item) {
        Long invoiceId = item.getInvoiceId();
        try {
            return ResponseEntity.ok(invoiceItemService.addItemToInvoice(invoiceId, item));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invoice ID not found");
        }
    }
}
