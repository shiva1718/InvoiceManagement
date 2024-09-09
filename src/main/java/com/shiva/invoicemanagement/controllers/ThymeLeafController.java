package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.CustomerDTO;
import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.services.CustomerService;
import com.shiva.invoicemanagement.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThymeLeafController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @GetMapping(value = {"/home", "/"})
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
        return "invoices";
//        List<InvoiceDTO> invoices = invoiceService.listAllInvoices();
//        if (invoices == null) {
//            return "redirect:/api/v1/invoices/";
//        } else {
//            model.addAttribute("invoices", invoices);
//            return "invoices";
//        }
    }

    @GetMapping("/invoices/new")
    public String newInvoice(Model model) {
        return "newinvoice";
    }

    @GetMapping("/customers/new")
    public String newCustomer(Model model) {
        return "newcustomer";
    }

    @GetMapping("/invoices/{id}")
    public String viewInvoice(Model model) {
        return "viewinvoice";
    }
}
