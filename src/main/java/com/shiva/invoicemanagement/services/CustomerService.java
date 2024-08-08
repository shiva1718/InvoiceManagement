package com.shiva.invoicemanagement.services;

import com.shiva.invoicemanagement.dto.CustomerDTO;
import com.shiva.invoicemanagement.repo.CustomerRepository;
import com.shiva.invoicemanagement.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<CustomerDTO> customerDtos = new ArrayList<>(customers.size());
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer);
            customerDtos.add(customerDTO);
        }
        return customerDtos;
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
