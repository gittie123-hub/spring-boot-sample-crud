package com.example.demo.service;

import com.example.demo.dto.OrderItemRequest;
import com.example.demo.dto.OrderRequest;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.OrderNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class CustomerOrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;


    public CustomerOrder createOrder(OrderRequest orderRequest){
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(()->new CustomerNotFoundException("Customer not found"));

        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemRequest orderItemRequest : orderRequest.getItems()){
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found: " + orderItemRequest.getProductId()));


            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
//

            orderItems.add(orderItem);

        }
        order.setItems(orderItems);

        return customerOrderRepository.save(order);

    }

    public CustomerOrder getOrderById(Long id) {
        return customerOrderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with"+id));
    }

}
