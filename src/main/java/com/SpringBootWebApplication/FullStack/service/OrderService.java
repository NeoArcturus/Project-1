package com.SpringBootWebApplication.FullStack.service;

import com.SpringBootWebApplication.FullStack.Communication.OrderResponse;
import com.SpringBootWebApplication.FullStack.Communication.UpdateOrder;
import com.SpringBootWebApplication.FullStack.entity.Order;
import com.SpringBootWebApplication.FullStack.entity.Product;
import com.SpringBootWebApplication.FullStack.entity.Transaction;
import com.SpringBootWebApplication.FullStack.exception.MultipleOrdersForSingleTransactionException;
import com.SpringBootWebApplication.FullStack.exception.OrderNotFoundException;
import com.SpringBootWebApplication.FullStack.exception.TransactionDoesNotExistException;
import com.SpringBootWebApplication.FullStack.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ProductService productService;

    public Order createOrder(OrderResponse response) throws TransactionDoesNotExistException, MultipleOrdersForSingleTransactionException {
        Order existingOrder = repository.findOrderByTransaction(transactionService.showTransactionById(response.getTransactionId()));
        if (existingOrder != null)
            throw new MultipleOrdersForSingleTransactionException("Only one order allowed per transaction!", new Exception());
        Order order = new Order();

        order.setTransaction(transactionService.showTransactionById(response.getTransactionId()));
        order.setOrderMessage(response.getOrderMessage());
        order.setOrderStatus(response.getOrderStatus());
        return repository.save(order);
    }

    public Order showOrder(String id) throws OrderNotFoundException {
        Order order = repository.findOrderById(id);
        if (order == null)
            throw new OrderNotFoundException("Order not found!", new Exception());
        return order;
    }

    public Order updateOrder(String id, UpdateOrder updateOrder) throws OrderNotFoundException {
        Order order = repository.findOrderById(id);
        if (order == null)
            throw new OrderNotFoundException("Order not found!", new Exception());
        order.setOrderMessage(updateOrder.getMessage());
        order.setOrderStatus(updateOrder.getStatus());
        return repository.save(order);
    }

    public Order cancelOrder(String id) throws OrderNotFoundException {
        Order order = repository.findOrderById(id);
        if (order == null)
            throw new OrderNotFoundException("Order not found!", new Exception());
        Transaction transaction = order.getTransaction();
        List<Product> productList = transaction.getProductList();
        List<Long> productQuantList = transaction.getProductQuantity();

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            product.setQuantity(product.getQuantity() + productQuantList.get(i));
            productService.updateProductFromTransaction(product);
        }
        order.setOrderMessage("Your order has been cancelled!");
        order.setOrderStatus("Cancelled");
        return repository.save(order);
    }
}
