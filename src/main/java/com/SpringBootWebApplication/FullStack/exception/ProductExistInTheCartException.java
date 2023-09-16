package com.SpringBootWebApplication.FullStack.exception;

public class ProductExistInTheCartException extends Exception {
    public ProductExistInTheCartException(String msg, Throwable e) {
        super(msg, e);
    }
}
