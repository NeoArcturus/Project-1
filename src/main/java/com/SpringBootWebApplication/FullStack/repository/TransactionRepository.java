package com.SpringBootWebApplication.FullStack.repository;

import com.SpringBootWebApplication.FullStack.entity.Transaction;
import com.SpringBootWebApplication.FullStack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTransactionById(String transactionId);

    Transaction findTransactionByUser(User userById);
}
