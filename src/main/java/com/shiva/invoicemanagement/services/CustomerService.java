package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.repo.CustomerRepository;
import com.shiva.invoicemanagement.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        byId.get().update(customer);
        return customerRepository.save(byId.get());
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
}
