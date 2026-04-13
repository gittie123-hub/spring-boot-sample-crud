package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllcustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerbyId(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }


}
