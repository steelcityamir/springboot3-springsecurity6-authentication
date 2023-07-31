package com.example.demo.service.account;

import com.example.demo.entity.Account;

public interface AccountService {
    Account registerAccount(Account account);
    boolean accountExists(String email);
    boolean activeAccountExists(String email);
}

