package com.galvanize.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.customer.model.Customer;
import com.galvanize.customer.responsedto.Response;
import com.galvanize.customer.service.CustomerService;
import org.apache.catalina.util.CustomObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController (CustomerService customerService){
        this.customerService=customerService;
    }


    @GetMapping("/api/customers")
    public List<Customer> getCustomers() throws IOException {
        return customerService.getCustomers();
    }


    @PostMapping("/api/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }


    @GetMapping("/api/customers/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) throws IOException {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("/api/customers/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer,@PathVariable Long customerId){
        return customerService.updateCustomer(customer);
    }
    @DeleteMapping("/api/customers/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId){
         customerService.deleteCustomerById(customerId);
         return "Customer deleted";
    }

}
