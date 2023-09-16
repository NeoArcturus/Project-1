package com.SpringBootWebApplication.FullStack.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String msg, Throwable e){
        super(msg, e);
    }
}
