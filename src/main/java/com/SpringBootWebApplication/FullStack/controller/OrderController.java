package com.SpringBootWebApplication.FullStack.controller;

import com.SpringBootWebApplication.FullStack.Communication.OrderResponse;
import com.SpringBootWebApplication.FullStack.Communication.UpdateOrder;
import com.SpringBootWebApplication.FullStack.entity.Order;
import com.SpringBootWebApplication.FullStack.exception.MultipleOrdersForSingleTransactionException;
import com.SpringBootWebApplication.FullStack.exception.OrderNotFoundException;
import com.SpringBootWebApplication.FullStack.exception.TransactionDoesNotExistException;
import com.SpringBootWebApplication.FullStack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/newOrder")
    public Order createOrder(@RequestBody OrderResponse response) throws TransactionDoesNotExistException, MultipleOrdersForSingleTransactionException {
        return service.createOrder(response);
    }

    @GetMapping("/order/{id}")
    public Order showOrder(@PathVariable String id) throws OrderNotFoundException {
        return service.showOrder(id);
    }

    @PostMapping("/updateOrder/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody UpdateOrder order) throws OrderNotFoundException {
        return service.updateOrder(id, order);
    }

    @PostMapping("/cancelOrder/{id}")
    public Order cancelOrder(@PathVariable String id) throws OrderNotFoundException {
        return service.cancelOrder(id);
    }
}
