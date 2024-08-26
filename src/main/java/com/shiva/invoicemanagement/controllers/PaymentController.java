package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.PaymentDTO;
import com.shiva.invoicemanagement.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

//    @PostMapping("/add")
    @PostMapping
    public ResponseEntity<?> addPayment(@RequestBody PaymentDTO payment) {
        if (payment == null) {
            return ResponseEntity.badRequest().body("Body not found");
        }
        return ResponseEntity.ok(paymentService.addPayment(payment));

    }
}
