package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.TokenService;
import com.example.demo.service.security.PasswordResetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResetPasswordController {

    private final PasswordResetService passwordResetService;
    private final TokenService tokenService;

    public ResetPasswordController(PasswordResetService passwordResetService, TokenService tokenService) {
        this.passwordResetService = passwordResetService;
        this.tokenService = tokenService;
    }

    /**
     * Shows the Reset Password page if token is valid
     *
     * User arrives at this page when clicking the reset link in their email
     *
     * @param token
     * @param model
     * @return
     */
    @GetMapping(value = "/reset")
    public String showResetPasswordPage(@RequestParam(required = false) String token, Model model) {
        if (token == null || token.isBlank()) {
            return "redirect:/login";
        }

        try {
            // Validate the token
            String email = tokenService.validateToken(token);

            // Needed to populate hidden input field so the reset form can identify the account
            model.addAttribute("email", email);

            return "reset-password";
        } catch (Exception e) {
            // If validation fails, set tokenError attribute so the page knows to show an error
            model.addAttribute("tokenError", "");
        }

        return "reset-password";
    }

    /**
     * Handles form submissions on Reset Password page
     *
     * @param account
     * @return
     */
    @PostMapping(value = "/reset")
    public String resetPassword(Account account) {
        if (account.getPassword() != null) {
            passwordResetService.resetPassword(account.getEmail(), account.getPassword());
        }

        return "redirect:/login";
    }
}
