package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.DuplicateFoundException;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
       boolean exists = customerRepository.existsByNameAndEmail(customer.getName(),customer.getEmail());
           if(exists) {
               throw new DuplicateFoundException("This customer already registered");
           }

           return customerRepository.save(customer);


    }

    public List<Customer> getAllcustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerbyId(Long id){
        return customerRepository.findById(id);

    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }



}
