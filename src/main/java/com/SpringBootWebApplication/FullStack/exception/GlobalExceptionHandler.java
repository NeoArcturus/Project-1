package com.SpringBootWebApplication.FullStack.exception;

import com.SpringBootWebApplication.FullStack.Communication.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity ExceptionHandler(CategoryNotFoundException e) {
        Error error = new Error(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity ExceptionHandler(ProductNotFoundException e) {
        Error error = new Error(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity ExceptionHandler(BadCredentialsException e) {
        Error error = new Error(HttpStatus.UNAUTHORIZED, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(CategoryExistsException.class)
    public ResponseEntity ExceptionHandler(CategoryExistsException e) {
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity ExceptionHandler(CartNotFoundException e) {
        Error error = new Error(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(ProductExistInTheCartException.class)
    public ResponseEntity ExceptionHandler(ProductExistInTheCartException e) {
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(TransactionDoesNotExistException.class)
    public ResponseEntity ExceptionHandler(TransactionDoesNotExistException e) {
        Error error = new Error(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity ExceptionHandler(PaymentException e) {
        Error error = new Error(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity ExceptionHandler(UserNotFoundException e) {
        Error error = new Error(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    public ResponseEntity ExceptionHandler(ProductNotAvailableException e) {
        Error error = new Error(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity ExceptionHandler(OrderNotFoundException e) {
        Error error = new Error(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(MultipleOrdersForSingleTransactionException.class)
    public ResponseEntity ExceptionHandler(MultipleOrdersForSingleTransactionException e) {
        Error error = new Error(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }
}
