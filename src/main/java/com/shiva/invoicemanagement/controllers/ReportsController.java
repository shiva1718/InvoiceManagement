package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/monthly-sales")
    public ResponseEntity<?> monthlySalesReport() {
        List<Map<String, String>> monthlySales = reportService.monthlySalesReport();
//        System.out.println("monthlySales = " + monthlySales);
        return ResponseEntity.ok(monthlySales);
    }

    @GetMapping("/top-customers")
    public ResponseEntity<?> topCustomersReport() {
        List<Map<String, String>> topCustomers = reportService.topCustomersReport();
        return ResponseEntity.ok(topCustomers);
    }

    @GetMapping("/top-items")
    public ResponseEntity<?> topItemsReport() {
        List<Map<String, String>> topTimes = reportService.topItemsReport();
        return ResponseEntity.ok(topTimes);
    }
}
