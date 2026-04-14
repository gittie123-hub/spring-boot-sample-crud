package com.example.demo.exceptions;

import com.example.demo.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoudException(CustomerNotFoundException exception) {
        ErrorResponse customernotfound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Customer not found");
        return new ResponseEntity<>(customernotfound, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DuplicateFoundException.class)
    public ResponseEntity<?> handleDuplicateNotFoundException(DuplicateFoundException exception){
            ErrorResponse duplicatefound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Customer already registered");
            return new ResponseEntity<>(duplicatefound, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException exception){
        ErrorResponse productnotfound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Product not found");
        return new ResponseEntity<>(productnotfound,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException exception){
        ErrorResponse ordernotfound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Order not found");
        return new ResponseEntity<>(ordernotfound,HttpStatus.NOT_FOUND);
    }
}
