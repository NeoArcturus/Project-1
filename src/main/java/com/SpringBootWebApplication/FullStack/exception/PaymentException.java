package com.SpringBootWebApplication.FullStack.exception;

public class PaymentException extends Exception {

    public PaymentException(String msg, Throwable e) {
        super(msg, e);
    }
}
