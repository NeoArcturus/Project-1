package com.SpringBootWebApplication.FullStack.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
