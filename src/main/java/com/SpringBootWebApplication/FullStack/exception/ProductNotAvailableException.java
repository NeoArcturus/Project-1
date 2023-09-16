package com.SpringBootWebApplication.FullStack.exception;

public class ProductNotAvailableException extends Exception {

    public ProductNotAvailableException(String msg, Throwable e) {
        super(msg, e);
    }
}
