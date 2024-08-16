package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.dto.CustomerDTO;
import com.shiva.invoicemanagement.entities.Customer;
import com.shiva.invoicemanagement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<?> listAllCustomers() {
        List<CustomerDTO> customers = customerService.listAllCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer found");
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
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
        List<CustomerDTO> customers = customerService.listAllCustomers();
        for (CustomerDTO customer : customers) {
            if (customer.getId().equals(id)) {
                return ResponseEntity.ok(customer.getInvoices());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }

    @GetMapping("balance/{id}")
    public ResponseEntity<?> getCustomerBalance(@PathVariable Long id) {
        return ResponseEntity.ok("Customer balance = " + customerService.getCustomerBalance(id));
    }

    @PutMapping("balance/update/{id}")
    public ResponseEntity<?> updateCustomerBalance(@PathVariable Long id) {
        return ResponseEntity.ok("Customer balance updated to " + customerService.updateCustomerBalance(id));
    }

}
