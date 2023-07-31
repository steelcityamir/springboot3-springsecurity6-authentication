package com.example.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * DefaultTokenService generates stateless time-limited tokens
 */
@Service
public class DefaultTokenService implements TokenService {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 1; // 1 hour

    // Changing this means that any active reset tokens will be invalidated
    private static final String SECRET_KEY = "Ft68t6w9DzB8aS1GmPf9Ht2E6W8mT7eR2S1g8K6z9D2b6E3T2N6v9Y2Q6x8W2F4J";

    @Override
    public String generateToken(String email, long expirationInMs) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationInMs);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))  // Use the secret key here
                .compact();
    }

    @Override
    public String validateToken(String token) throws Exception {
        try {
            String email = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))  // Use the secret key here
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return email;
        } catch (Exception e) {
            throw new Exception("Invalid token");
        }
    }
}
