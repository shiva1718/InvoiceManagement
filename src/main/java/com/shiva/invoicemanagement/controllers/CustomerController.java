package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.CustomerDTO;
import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.entities.Customer;
import com.shiva.invoicemanagement.repo.UserRepository;
import com.shiva.invoicemanagement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        System.out.println("received customer add req");
        System.out.println(customer);
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> listAllCustomers() {
        List<CustomerDTO> customers = customerService.listAllCustomers();
        System.out.println("received customer list req");
//        if (customers.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer found");
//        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id, Principal principal) {
        String name = principal.getName();
        System.out.println("Principal name = " + name);
        userRepository.findByUsername(name).ifPresent(user -> System.out.println("User role = " + user.getRole()));
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.ok("Customer deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }

    @GetMapping("/{id}/invoices")
    public ResponseEntity<?> getInvoicesByCustomer(@PathVariable Long id) {
        List<InvoiceDTO> invoices = customerService.listCustomerInvoices(id);
        if (invoices != null) {
            return ResponseEntity.ok(invoices);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }

    @GetMapping("balance/{id}")
    public ResponseEntity<?> getCustomerBalance(@PathVariable Long id) {
        return ResponseEntity.ok("Customer balance = " + customerService.getCustomerBalance(id));
    }
}
