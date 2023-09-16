package com.SpringBootWebApplication.FullStack.exception;

public class CategoryExistsException extends Exception {
    public CategoryExistsException(String message, Throwable e) {
        super(message, e);
    }
}
