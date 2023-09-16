package com.SpringBootWebApplication.FullStack.exception;

public class TransactionDoesNotExistException extends Exception {

    public TransactionDoesNotExistException(String msg, Throwable e) {
        super(msg, e);
    }
}
