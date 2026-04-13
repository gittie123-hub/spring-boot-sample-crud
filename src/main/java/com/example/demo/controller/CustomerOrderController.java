package com.example.demo.controller;

import com.example.demo.dto.OrderRequest;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.service.CustomerOrderService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@NoArgsConstructor
@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @JsonIgnore
    @PostMapping
    public CustomerOrder createOrder(@RequestBody OrderRequest orderRequest) {
        return customerOrderService.createOrder(orderRequest);
    }
    @JsonIgnore
    @GetMapping("/{id}")
    public CustomerOrder getOrderById(@PathVariable Long id) {
        return customerOrderService.getOrderById(id);
    }
}
