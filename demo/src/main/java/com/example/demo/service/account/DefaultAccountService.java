package com.example.demo.service.account;

import com.example.demo.entity.Account;
import com.example.demo.exception.AccountExistsException;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    @Autowired
    public DefaultAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, MessageSource messageSource) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
    }

    @Override
    public Account registerAccount(Account account) {
        if (accountExists(account.getEmail())) {
            String errorMessage = messageSource.getMessage("error.register.account.duplicate.email", null, Locale.getDefault());
            throw new AccountExistsException(errorMessage);
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setEnabled(true);
        return accountRepository.save(account);
    }

    @Override
    public boolean accountExists(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public boolean activeAccountExists(String email) {
        return accountRepository.existsByEmailAndEnabled(email, true);
    }
}
