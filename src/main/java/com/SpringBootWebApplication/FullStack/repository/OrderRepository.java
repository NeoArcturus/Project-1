package com.SpringBootWebApplication.FullStack.repository;

import com.SpringBootWebApplication.FullStack.entity.Order;
import com.SpringBootWebApplication.FullStack.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findOrderById(String id);

    Order findOrderByTransaction(Transaction transaction);
}
