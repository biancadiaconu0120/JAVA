package com.modul2.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Maps to store verification codes and their expiration timestamps (in milliseconds)
    private final Map<String, String> verificationCodes = new HashMap<>();
    private final Map<String, Long> expirationTimes = new HashMap<>();

    private final Random random = new Random();

    /**
     * Sends a verification email with a randomly generated 6-digit code that expires in 10 minutes.
     *
     * @param toEmail the recipient's email address
     * @return the generated verification code
     */
    public String sendVerificationEmail(String toEmail) {
        // Generate a random 6-digit number (between 100000 and 999999)
        int codeInt = random.nextInt(900000) + 100000;
        String code = String.valueOf(codeInt);

        verificationCodes.put(toEmail, code);
        expirationTimes.put(toEmail, System.currentTimeMillis() + 10 * 60 * 1000); // 10 minutes

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(toEmail);
        message.setSubject("Verify Your Account");
        message.setText("Your verification code is: " + code + "\nIt expires in 10 minutes.");

        mailSender.send(message);
        return code;
    }

    /**
     * Verifies if the provided code for the given email is valid and has not expired.
     *
     * @param email the email address to verify
     * @param code  the verification code provided by the user
     * @return true if the code is valid and not expired; false otherwise
     */
    public boolean verifyUser(String email, String code) {
        String storedCode = verificationCodes.get(email);
        Long expiresAt = expirationTimes.get(email);
        if (storedCode == null || expiresAt == null) {
            return false;
        }
        if (System.currentTimeMillis() > expiresAt) {
            verificationCodes.remove(email);
            expirationTimes.remove(email);
            return false;
        }
        if (storedCode.equals(code)) {
            verificationCodes.remove(email);
            expirationTimes.remove(email);
            return true;
        }
        return false;
    }
}
