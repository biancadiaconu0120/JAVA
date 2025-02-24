package com.modul2.learning.service;


import com.modul2.learning.entities.User;

import com.modul2.learning.repository.UserRepository;
import com.modul2.learning.utils.PasswordUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//clasa de business logic (un serviciu pe care aplicatia il ofera)

@Service
//@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // REGISTRATION
    public User create(User user) {

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        try {
            // Hash the password and set verifiedAccount to false
            user.setPassword(PasswordUtils.hashPassword(user.getPassword().trim()));
            user.setVerifiedAccount(false);

            // Save the user
            User savedUser = userRepository.save(user);


            try {
                emailService.sendVerificationEmail(user.getEmail());
            } catch (Exception emailEx) {
                throw new RuntimeException("User registered but failed to send verification email", emailEx);
            }
            return savedUser;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to register user", ex);
        }
    }

    // VERIFY ACCOUNT
    public boolean verifyAccount(String email, String code) {
        // Check the verification code (from in-memory storage)
        boolean valid = emailService.verifyUser(email, code);
        if (valid) {
            // Retrieve the user by email and update the verified flag
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            user.setVerifiedAccount(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User login(String email, String password) {
        // Normalize email
        String normalizedEmail = email.toLowerCase().trim();

        // Retrieve the user entity by normalized email
        User user = userRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found for email: " + normalizedEmail));

        // Log raw input details for debugging
        System.out.println("Raw password input: '" + password + "'");
        String trimmedPassword = password.trim();
        System.out.println("Trimmed password: '" + trimmedPassword + "', length: " + trimmedPassword.length());

        // Check if the input appears to be a SHA-256 hash (64 hex characters)
        boolean isAlreadyHashed = trimmedPassword.matches("^[a-fA-F0-9]{64}$");
        String hashedInput;
        if (isAlreadyHashed) {
            System.out.println("Detected already hashed input.");
            hashedInput = trimmedPassword;
        } else {
            hashedInput = PasswordUtils.hashPassword(trimmedPassword);
        }

        System.out.println("Computed hash: " + hashedInput);
        System.out.println("Stored hash: " + user.getPassword());

        if (!user.getPassword().equals(hashedInput)) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!user.isVerifiedAccount()) {
            throw new IllegalStateException("Account is not verified");
        }

        return user;
    }



    // Retrieve a user by ID
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    // Retrieve all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Update an existing user's basic details
    public User update(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setYearOfBirth(user.getYearOfBirth());
        existingUser.setGender(user.getGender());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setCountry(user.getCountry());

        return userRepository.save(existingUser);
    }

    // Delete a user by ID
    public void delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

}
