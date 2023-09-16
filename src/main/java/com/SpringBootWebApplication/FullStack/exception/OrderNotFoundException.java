package com.SpringBootWebApplication.FullStack.exception;

public class OrderNotFoundException extends Exception {

    public OrderNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}
