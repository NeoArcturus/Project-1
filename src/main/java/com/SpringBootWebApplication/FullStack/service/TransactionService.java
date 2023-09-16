package com.SpringBootWebApplication.FullStack.service;

import com.SpringBootWebApplication.FullStack.Communication.OrderRequest;
import com.SpringBootWebApplication.FullStack.Communication.OrderResponse;
import com.SpringBootWebApplication.FullStack.Communication.PaymentRequest;
import com.SpringBootWebApplication.FullStack.entity.Product;
import com.SpringBootWebApplication.FullStack.entity.Transaction;
import com.SpringBootWebApplication.FullStack.exception.*;
import com.SpringBootWebApplication.FullStack.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    public Transaction createTransaction(OrderRequest request) throws UserNotFoundException, CartNotFoundException, ProductNotFoundException {
        Transaction transaction = new Transaction();
        transaction.setUser(userService.getUserById(request.getUserId()));

        List<Product> orders = new ArrayList<Product>();
        List<Long> orderQuantity = new ArrayList<Long>();

        double amount = 0;

        for (String i : request.getProductNameList()) {
            Product product = cartService.getProductByName(i, cartService.showCartByUserId(request.getUserId()).getId());
            Long quantity = cartService.getProductQuantityByName(i, cartService.showCartByUserId(request.getUserId()).getId());
            amount += product.getPrice() * quantity;
            orders.add(product);
            orderQuantity.add(quantity);
        }
        Transaction existingTransaction = repository.findTransactionByUser(userService.getUserById(request.getUserId()));
        if (existingTransaction != null)
            repository.delete(existingTransaction);

        transaction.setProductList(orders);
        transaction.setProductQuantity(orderQuantity);
        transaction.setAmount(amount);
        transaction.setStatus("Pending");
        return repository.save(transaction);
    }

    public OrderResponse paymentMode(PaymentRequest request) throws TransactionDoesNotExistException, PaymentException, ProductNotAvailableException, ProductNotFoundException {
        Transaction transaction = repository.findTransactionById(request.getTransactionId());
        if (transaction == null)
            throw new TransactionDoesNotExistException("Transaction not found!", new Exception());
        if (transaction.getAmount() > request.getPaymentAmount())
            throw new PaymentException("Insufficient balance!", new Exception());
        String status = transaction.getStatus();
        if (status.equals("Received"))
            throw new PaymentException("Transaction has been closed already!", new Exception());
        if (status.equals("Rejected"))
            throw new PaymentException("Transaction has been rejected due to reasons!", new Exception());

        transaction.setMode(request.getMode());

        List<Product> orderList = transaction.getProductList();
        List<Long> orderQuantityList = transaction.getProductQuantity();

        for (int i = 0; i < orderList.size(); i++) {
            Product product = productService.getProductByName(orderList.get(i).getName());
            if (product.getQuantity() < orderQuantityList.get(i)) {
                transaction.setStatus("Rejected");
                repository.save(transaction);
                throw new ProductNotAvailableException("Product not available! Order Cancelled! Your money will be refunded", new Exception());
            }
            product.setQuantity(product.getQuantity() - orderQuantityList.get(i));
            productService.updateProductFromTransaction(product);
        }
        transaction.setStatus("Received");
        transaction.setPaid(request.getPaymentAmount());
        transaction.setBalance(request.getPaymentAmount() - transaction.getAmount());
        OrderResponse response = getResponse(transaction);
        repository.save(transaction);
        return response;
    }

    private static OrderResponse getResponse(Transaction transaction) {
        OrderResponse response = new OrderResponse();
        response.setOrderMessage("Your order has been received!");
        response.setOrderStatus("Received");
        response.setTransactionId(transaction.getId());
        return response;
    }

    public Transaction showTransactionById(String id) throws TransactionDoesNotExistException {
        Transaction transaction = repository.findTransactionById(id);
        if (transaction == null)
            throw new TransactionDoesNotExistException("Transaction not found!", new Exception());
        return transaction;
    }

    public List<Product> getProducts(String id) throws TransactionDoesNotExistException {
        Transaction transaction = repository.findTransactionById(id);
        if (transaction == null)
            throw new TransactionDoesNotExistException("Transaction not found!", new Exception());
        return transaction.getProductList();
    }

    public List<Long> getProductQuantList(String id) throws TransactionDoesNotExistException {
        Transaction transaction = repository.findTransactionById(id);
        if (transaction == null)
            throw new TransactionDoesNotExistException("Transaction not found!", new Exception());
        return transaction.getProductQuantity();
    }

    public String checkTransactionStatus(String id) throws TransactionDoesNotExistException {
        Transaction transaction = repository.findTransactionById(id);
        if (transaction == null)
            throw new TransactionDoesNotExistException("Transaction not found!", new Exception());
        return transaction.getStatus();
    }
}
