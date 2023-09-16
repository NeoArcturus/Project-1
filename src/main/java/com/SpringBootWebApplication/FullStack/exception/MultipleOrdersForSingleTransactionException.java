package com.SpringBootWebApplication.FullStack.exception;

public class MultipleOrdersForSingleTransactionException extends Exception {

    public MultipleOrdersForSingleTransactionException(String msg, Throwable e) {
        super(msg, e);
    }
}
