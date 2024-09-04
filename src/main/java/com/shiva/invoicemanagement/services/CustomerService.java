package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.dto.CustomerDTO;
import com.shiva.invoicemanagement.dto.InvoiceDTO;
import com.shiva.invoicemanagement.exception.CustomerNotFoundException;
import com.shiva.invoicemanagement.repo.CustomerRepository;
import com.shiva.invoicemanagement.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<CustomerDTO> listAllCustomers() {
//        return customerRepository.findAll();
        List<Customer> customers = customerRepository.findAll();
        System.out.println("found customers" + customers);
        List<CustomerDTO> customerDtos = new ArrayList<>(customers.size());
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer);
            customerDtos.add(customerDTO);
        }
        System.out.println("returned customerDTOs");
        return customerDtos;
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        System.out.println("found customer = " + customer);
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(customer);
    }

    public CustomerDTO updateCustomer(Long id, Customer customer) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        byId.get().update(customer);
        return new CustomerDTO(customerRepository.save(byId.get()));
//        customer.setId(id);
//        return customerRepository.save(customer);
    }

    public boolean deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return true;
        }
        return false;
    }

    public double getCustomerBalance(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer ID not found");
        }
        return customer.get().getBalance();
    }

    public String updateCustomerBalance(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer ID not found");
        }
//        customer.get().setBalance(0);
        customer.get().updateBalance();
        customerRepository.save(customer.get());
        return "Customer balance updated successfully";
    }

    public List<InvoiceDTO> listCustomerInvoices(long id) {
        List<CustomerDTO> customers = listAllCustomers();
        for (CustomerDTO customer : customers) {
            if (customer.getId().equals(id)) {
                return customer.getInvoices();
            }
        }
        return null;
    }
}
