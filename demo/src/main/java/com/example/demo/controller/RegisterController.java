package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.exception.AccountExistsException;
import com.example.demo.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegisterController {

    private final AccountService accountService;

    @Autowired
    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerAccount(Account account, Model model) {
        try {
            accountService.registerAccount(account);
            model.addAttribute("successMessage", "Your account has been successfully created. We've sent you an email confirmation.  Click the link in the email to verify your email address.");
            return "register";
        } catch (AccountExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
}
