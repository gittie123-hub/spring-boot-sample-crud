package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
   private CustomerService customerService;

    private CustomerRepository repository;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){

        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);

    }
    @GetMapping
    public List<Customer> getAllCustomers() {

        return customerService.getAllcustomers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

        Customer customer = customerService.getCustomerbyId(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found with id "+ id));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);
    }

}
