package com.SpringBootWebApplication.FullStack.exception;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
