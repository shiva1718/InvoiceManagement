package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.CustomerDTO;
import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.services.CustomerService;
import com.shiva.invoicemanagement.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThymeLeafController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        return "customers";
//        List<CustomerDTO> customers = customerService.listAllCustomers();
//        if (customers == null) {
//            return "redirect:/api/v1/customers/";
//        } else {
//            model.addAttribute("customers", customers);
//            return "customers";
//        }
    }

    @GetMapping("/invoices")
    public String invoices(Model model) {
        List<InvoiceDTO> invoices = invoiceService.listAllInvoices();
//        System.out.println("Invoices = " + invoices);
        if (invoices == null) {
            return "redirect:/api/v1/invoices/";
        } else {
            model.addAttribute("invoices", invoices);
            return "invoices";
        }
    }
}
