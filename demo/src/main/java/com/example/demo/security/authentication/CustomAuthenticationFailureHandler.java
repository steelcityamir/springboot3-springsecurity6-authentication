package com.example.demo.security.authentication;

import com.example.demo.service.LoginAttemptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Configures Spring Security to redirect to /login?disabled if a disabled account attempts
 * to login.  This allows us to customize the error message on the page.
 *
 * Also records failed login attempts to the DB.
 */
@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private LoginAttemptService loginAttemptService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final String ip = request.getRemoteAddr();
        final String email = request.getParameter("username");
        log.info("Failed login for {} from {}", email, ip);

        loginAttemptService.saveFailedAttempt(email, ip);

        if (exception instanceof DisabledException) {
            redirectStrategy.sendRedirect(request, response, "/login?disabled");
        } else {
            redirectStrategy.sendRedirect(request, response, "/login?error");
        }
    }
}

