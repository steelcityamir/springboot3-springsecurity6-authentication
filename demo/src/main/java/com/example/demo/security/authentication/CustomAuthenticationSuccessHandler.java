package com.example.demo.security.authentication;

import com.example.demo.service.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Configures Spring Security to redirect to /home and record successful login
 * to the DB
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private LoginAttemptService loginAttemptService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        final String ip = request.getRemoteAddr();
        final String email = request.getParameter("username");
        log.info("Successful login for {} from {}", email, ip);

        loginAttemptService.saveSuccessfulAttempt(email, ip);
        redirectStrategy.sendRedirect(request, response, "/home");
    }
}

