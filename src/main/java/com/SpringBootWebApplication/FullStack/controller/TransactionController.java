package com.SpringBootWebApplication.FullStack.controller;

import com.SpringBootWebApplication.FullStack.Communication.OrderRequest;
import com.SpringBootWebApplication.FullStack.Communication.OrderResponse;
import com.SpringBootWebApplication.FullStack.Communication.PaymentRequest;
import com.SpringBootWebApplication.FullStack.entity.Transaction;
import com.SpringBootWebApplication.FullStack.exception.*;
import com.SpringBootWebApplication.FullStack.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/transaction")
    public Transaction newOrder(@RequestBody OrderRequest request) throws UserNotFoundException, ProductNotFoundException, CartNotFoundException {
        return service.createTransaction(request);
    }

    @PostMapping("/payment")
    public OrderResponse placeOrder(@RequestBody PaymentRequest request) throws TransactionDoesNotExistException, PaymentException, ProductNotAvailableException, ProductNotFoundException {
        return service.paymentMode(request);
    }

    @GetMapping("/showTransaction/{id}")
    public Transaction showTransactionById(@PathVariable String id) throws TransactionDoesNotExistException {
        return service.showTransactionById(id);
    }
}
