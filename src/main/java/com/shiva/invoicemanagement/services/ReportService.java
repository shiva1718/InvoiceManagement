package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, String>> monthlySalesReport() {
        String sql = "SELECT MONTH(date) AS month, SUM(total_amount) AS sales " +
                "FROM invoice " +
                "GROUP BY MONTH(date) " +
                "ORDER BY MONTH(date)";
        List<Map<String, String>> monthlySalesArray = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, String> row = new HashMap<>();
            row.put("month", Month.of(rs.getInt("month")).toString());
            row.put("sales", rs.getString("sales"));
            return row;
        });
//        Map<Month, Double> monthlySales = new HashMap<>();
//        invoiceRepository.findAll().forEach(invoice -> {
//            Month month = Month.of(invoice.getDate().getMonth() + 1);
//            Double totalAmount = invoice.getTotalAmount();
//            monthlySales.put(month, monthlySales.getOrDefault(month, 0.0) + totalAmount);
//        });
//        Map<String, String>[] monthlySalesArray = new Map[12];
//        for (int i = 0; i < 12; i++) {
//            monthlySalesArray[i] = new HashMap<>();
//            monthlySalesArray[i].put("month", Month.of(i + 1).toString());
//            monthlySalesArray[i].put("sales", monthlySales.getOrDefault(Month.of(i + 1), 0.0).toString());
////            monthlySalesArray[i].put(Month.of(i + 1), monthlySales.getOrDefault(Month.of(i + 1), 0.0));
//        }
        return monthlySalesArray;
    }

    public List<Map<String, String>> topCustomersReport() {
        String sql = "SELECT c.name, SUM(i.total_amount) AS total_purchases " +
                "FROM customer c " +
                "JOIN invoice i ON c.id = i.customer_id " +
                "GROUP BY c.name " +
                "ORDER BY total_purchases DESC " +
                "LIMIT 10";
        List<Map<String, String>> topCustomersArray = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, String> row = new HashMap<>();
            row.put("name", rs.getString("name"));
            row.put("totalPurchases", rs.getString("total_purchases"));
            return row;
        });
//        invoiceRepository.findAll().forEach(invoice -> {
//            String customerName = invoice.getCustomer().getName();
//            Double totalAmount = invoice.getTotalAmount();
//            topCustomers.put(customerName, topCustomers.getOrDefault(customerName, 0.0) + totalAmount);
//        });
//        Map<String, String>[] topCustomersArray = new Map[topCustomers.size()];
//        int i = 0;
//        for (Map.Entry<String, Double> entry : topCustomers.entrySet()) {
//            topCustomersArray[i] = new HashMap<>();
//            topCustomersArray[i].put("name", entry.getKey());
//            topCustomersArray[i].put("totalPurchases", String.valueOf(entry.getValue()));
//            i++;
//        }
//        Arrays.sort(topCustomersArray, (a, b) -> {
//            Double aTotalPurchases = Double.parseDouble(a.get("totalPurchases"));
//            Double bTotalPurchases = Double.parseDouble(b.get("totalPurchases"));
//            return bTotalPurchases.compareTo(aTotalPurchases);
//        });
        return topCustomersArray;
    }

    public List<Map<String, String>> topItemsReport() {
        String sql = "select item.name as name, sum(item.total_amount) as total_amount\n" +
                "from invoice_item item\n" +
                "         join invoice inv on item.invoice_id = inv.id\n" +
                "group by item.name\n" +
                "order by sum(item.total_amount) desc\n" +
                "limit 10;";
        List<Map<String, String>> topItemsArray = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, String> row = new HashMap<>();
            row.put("name", rs.getString("name"));
            row.put("sales", rs.getString("total_amount"));
            return row;
        });
//        invoiceRepository.findAll().forEach(invoice -> {
//            invoice.getItems().forEach(item -> {
//                String itemName = item.getName();
//                Double totalAmount = item.getTotalAmount();
//                topItems.put(itemName, topItems.getOrDefault(itemName, 0.0) + totalAmount);
//            });
//        });
//        System.out.println("topItems = " + topItems);
//        Map<String, String>[] topItemsArray = new Map[topItems.size()];
//        int i = 0;
//        for (Map.Entry<String, Double> entry : topItems.entrySet()) {
//            topItemsArray[i] = new HashMap<>();
//            topItemsArray[i].put("name", entry.getKey());
//            topItemsArray[i].put("sales", String.valueOf(entry.getValue()));
//            i++;
//        }
//        Arrays.sort(topItemsArray, (a, b) -> {
//            Double aTotalSales = Double.parseDouble(a.get("sales"));
//            Double bTotalSales = Double.parseDouble(b.get("sales"));
//            return bTotalSales.compareTo(aTotalSales);
//        });
//        System.out.println("topItemsArray = " + Arrays.toString(topItemsArray));
        return topItemsArray;
    }
}
