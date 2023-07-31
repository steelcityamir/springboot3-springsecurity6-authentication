package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.account.AccountService;
import com.example.demo.service.email.EmailService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class ForgotPasswordController {

    private final EmailService emailService;
    private final AccountService accountService;
    private final MessageSource messageSource;

    public ForgotPasswordController(EmailService emailService, AccountService accountService, MessageSource messageSource) {
        this.emailService = emailService;
        this.accountService = accountService;
        this.messageSource = messageSource;
    }

    /**
     * Shows the Forgot Password page
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/forgot")
    public String showForgotPasswordPage(Model model) {
        return "forgot-password";
    }

    /**
     * Handles form submissions for Forgot Password page
     * @param account
     * @return
     *
     * Note: The input field name in the page must be fullName in order for the binding
     * to work and populate the Account object.
     */
    @PostMapping(value = "/forgot")
    public String sendPasswordResetLink(Account account, Model model) {
        if (account.getEmail() != null) {
            // Check if the active account exists
            if (accountService.activeAccountExists(account.getEmail())) {
                // Send password reset link
                emailService.sendPasswordResetEmail(account.getEmail());
            }
        }

        model.addAttribute("success", messageSource.getMessage("success.reset.password.email.sent", null, Locale.getDefault()));
        return "forgot-password";
    }
}