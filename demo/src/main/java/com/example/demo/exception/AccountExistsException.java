package com.example.demo.exception;

public class AccountExistsException extends RuntimeException {
    public AccountExistsException(String errorMessage) {
        super(errorMessage);
    }
}
