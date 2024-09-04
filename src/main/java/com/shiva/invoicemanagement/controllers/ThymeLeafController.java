package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThymeLeafController {

    @Autowired
    private CustomerService customerService;

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
        List<InvoiceDTO> invoices = customerService.listCustomerInvoices(1);
        if (invoices == null) {
            return "redirect:/api/v1/customers/";
        } else {
            model.addAttribute("customers", invoices);
            return "customers";
        }
    }
}
