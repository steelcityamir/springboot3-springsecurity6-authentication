package com.example.demo.service;

public interface LoginAttemptService {
    void saveSuccessfulAttempt(String email, String ip);
    void saveFailedAttempt(String email, String ip);
}
