package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Map<String, String>[] monthlySalesReport() {
        Map<Month, Double> monthlySales = new HashMap<>();
//        monthlySales.put(Month.JANUARY, 0.0);
//        monthlySales.put(Month.FEBRUARY, 0.0);
//        monthlySales.put(Month.MARCH, 0.0);
//        monthlySales.put(Month.APRIL, 0.0);
//        monthlySales.put(Month.MAY, 0.0);
//        monthlySales.put(Month.JUNE, 0.0);
//        monthlySales.put(Month.JULY, 0.0);
//        monthlySales.put(Month.AUGUST, 0.0);
//        monthlySales.put(Month.SEPTEMBER, 0.0);
//        monthlySales.put(Month.OCTOBER, 0.0);
//        monthlySales.put(Month.NOVEMBER, 0.0);
//        monthlySales.put(Month.DECEMBER, 0.0);
        invoiceRepository.findAll().forEach(invoice -> {
            Month month = Month.of(invoice.getDate().getMonth() + 1);
            Double totalAmount = invoice.getTotalAmount();
            monthlySales.put(month, monthlySales.getOrDefault(month, 0.0) + totalAmount);
        });
        Map<String, String>[] monthlySalesArray = new Map[12];
        for (int i = 0; i < 12; i++) {
            monthlySalesArray[i] = new HashMap<>();
            monthlySalesArray[i].put("month", Month.of(i + 1).toString());
            monthlySalesArray[i].put("sales", monthlySales.getOrDefault(Month.of(i + 1), 0.0).toString());
//            monthlySalesArray[i].put(Month.of(i + 1), monthlySales.getOrDefault(Month.of(i + 1), 0.0));
        }
        return monthlySalesArray;
    }

    public Map<String, String>[] topCustomersReport() {
        Map<String, Double> topCustomers = new HashMap<>();
        invoiceRepository.findAll().forEach(invoice -> {
            String customerName = invoice.getCustomer().getName();
            Double totalAmount = invoice.getTotalAmount();
            topCustomers.put(customerName, topCustomers.getOrDefault(customerName, 0.0) + totalAmount);
        });
        Map<String, String>[] topCustomersArray = new Map[topCustomers.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : topCustomers.entrySet()) {
            topCustomersArray[i] = new HashMap<>();
            topCustomersArray[i].put("name", entry.getKey());
            topCustomersArray[i].put("totalPurchases", String.valueOf(entry.getValue()));
            i++;
        }
        Arrays.sort(topCustomersArray, (a, b) -> {
            Double aTotalPurchases = Double.parseDouble(a.get("totalPurchases"));
            Double bTotalPurchases = Double.parseDouble(b.get("totalPurchases"));
            return bTotalPurchases.compareTo(aTotalPurchases);
        });
        return topCustomersArray;
    }

    public Map<String, String>[] topItemsReport() {
        Map<String, Double> topItems = new HashMap<>();
        invoiceRepository.findAll().forEach(invoice -> {
            invoice.getItems().forEach(item -> {
                String itemName = item.getName();
                Double totalAmount = item.getTotalAmount();
                topItems.put(itemName, topItems.getOrDefault(itemName, 0.0) + totalAmount);
            });
        });
        System.out.println("topItems = " + topItems);
        Map<String, String>[] topItemsArray = new Map[topItems.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : topItems.entrySet()) {
            topItemsArray[i] = new HashMap<>();
            topItemsArray[i].put("name", entry.getKey());
            topItemsArray[i].put("sales", String.valueOf(entry.getValue()));
            i++;
        }
        Arrays.sort(topItemsArray, (a, b) -> {
            Double aTotalSales = Double.parseDouble(a.get("sales"));
            Double bTotalSales = Double.parseDouble(b.get("sales"));
            return bTotalSales.compareTo(aTotalSales);
        });
        System.out.println("topItemsArray = " + Arrays.toString(topItemsArray));
        return topItemsArray;
    }
}
