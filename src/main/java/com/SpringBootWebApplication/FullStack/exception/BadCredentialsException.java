package com.SpringBootWebApplication.FullStack.exception;

public class BadCredentialsException extends org.springframework.security.authentication.BadCredentialsException {
    public BadCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
