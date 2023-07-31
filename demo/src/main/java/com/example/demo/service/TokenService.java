package com.example.demo.service;

/**
 * Interface for generating tokens for password resets and email confirmations
 */
public interface TokenService {
    /**
     * Generates token with expiration date
     *
     * @param email
     * @param expirationInMs
     * @return
     */
    String generateToken(String email, long expirationInMs);

    /**
     * Returns email from a valid token or throws an exception if invalid
     * @param token
     * @return
     * @throws Exception
     */
    String validateToken(String token) throws Exception;
}
