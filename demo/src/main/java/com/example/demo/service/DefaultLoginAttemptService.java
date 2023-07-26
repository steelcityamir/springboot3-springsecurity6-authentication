package com.example.demo.service;

import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * DefaultLoginAttemptService is responsible for recording failed and successful login attempts
 */
@Service
public class DefaultLoginAttemptService implements LoginAttemptService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public void saveSuccessfulAttempt(String email, String ip) {
        Login login = generateLogin(email, ip);
        login.setSuccess(true);
        loginRepository.save(login);
    }

    @Override
    public void saveFailedAttempt(String email, String ip) {
        Login login = generateLogin(email, ip);
        login.setSuccess(false);
        loginRepository.save(login);
    }

    private Login generateLogin(String email, String ip) {
        Login login = new Login();
        login.setEmail(email);
        login.setCreatedOn(LocalDateTime.now());
        login.setIp(ip);
        return login;
    }
}
