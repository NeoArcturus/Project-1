package com.SpringBootWebApplication.FullStack.exception;

public class CartNotFoundException extends Exception {

    public CartNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}
