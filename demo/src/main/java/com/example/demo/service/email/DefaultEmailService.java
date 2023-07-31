package com.example.demo.service.email;

import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailService implements EmailService {
    @Value("${app.base.url}")
    private String appBaseUrl;

    @Value("${sender.email.address}")
    private String senderEmailAddress;

    // Spring autoconfigures this when it sees mail settings in application.properties
    private final JavaMailSender emailSender;

    private final TokenService tokenService;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 1; // 1 hour

    public DefaultEmailService(JavaMailSender emailSender, TokenService tokenService) {
        this.emailSender = emailSender;
        this.tokenService = tokenService;
    }

    /**
     * Sends a plain text email with password reset link
     *
     * @param email
     */
    @Override
    public void sendPasswordResetEmail(String email) {
        String resetToken = tokenService.generateToken(email, EXPIRATION_TIME);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmailAddress);
        message.setTo(email);
        message.setSubject("Password Reset");

        // Compose the email content
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Hello,\n\n")
                .append("We've received a request to reset your password.\n")
                .append("If you didn't initiate this request, you can ignore this email.\n")
                .append("To reset your password, please click the link below:\n")
                .append(appBaseUrl)
                .append("/reset?token=")
                .append(resetToken)
                .append("\n\n")
                .append("Thank you,\n")
                .append("Support Team");

        message.setText(bodyBuilder.toString());
        emailSender.send(message);
    }

    @Override
    public void sendVerificationEmail(String email) {
        String verifyToken = tokenService.generateToken(email, EXPIRATION_TIME);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmailAddress);
        message.setTo(email);
        message.setSubject("Verify Email Address");

        // Compose the email content
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Hello,\n\n")
                .append("Thanks for signing up.\n")
                .append("If you didn't initiate this request, you can ignore this email.\n")
                .append("To confirm your email address, please click the link below:\n")
                .append(appBaseUrl)
                .append("/verify?token=")
                .append(verifyToken)
                .append("\n\n")
                .append("Thank you,\n")
                .append("Support Team");

        message.setText(bodyBuilder.toString());
        emailSender.send(message);
    }
}

