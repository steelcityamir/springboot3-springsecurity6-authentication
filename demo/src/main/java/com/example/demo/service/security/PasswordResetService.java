package com.example.demo.service.security;

public interface PasswordResetService {
    /**
     * Resets account with given email with given password
     * @param email
     * @param password
     */
    void resetPassword(String email, String password);
}