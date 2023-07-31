package com.example.demo.controller;

import com.example.demo.service.TokenService;
import com.example.demo.service.account.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VerifyController {

    private final AccountService accountService;
    private final TokenService tokenService;

    public VerifyController(AccountService accountService, TokenService tokenService) {
        this.accountService = accountService;
        this.tokenService = tokenService;
    }

    /**
     * Shows the Email Verified Confirmation page
     *
     * User arrives at this page when clicking the email verify link in their email
     *
     * @param token
     * @param model
     * @return
     */
    @GetMapping(value = "/verify")
    public String showVerifyPage(@RequestParam(required = false) String token, Model model) {
        if (token == null || token.isBlank()) {
            return "redirect:/login";
        }

        try {
            // Validate the token
            String email = tokenService.validateToken(token);

            accountService.enableAccount(email);

            return "verify-email";
        } catch (Exception e) {
            // If validation fails, set tokenError attribute so the page knows to show an error
            model.addAttribute("tokenError", "");
        }

        return "verify-email";
    }
}
