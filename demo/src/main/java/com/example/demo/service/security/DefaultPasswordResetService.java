package com.example.demo.service.security;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultPasswordResetService implements PasswordResetService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultPasswordResetService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void resetPassword(String email, String password) {
        // Find the account by email
        Account account = accountRepository.findByEmail(email);

        if (account != null) {
            // Encode the new password and set it
            account.setPassword(passwordEncoder.encode(password));
            // Save the updated account
            accountRepository.save(account);
        } else {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }
    }
}
