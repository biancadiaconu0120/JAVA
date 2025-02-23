package com.modul2.learning.service;

import com.modul2.learning.entities.Application;
import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.User;
import com.modul2.learning.repository.ApplicationRepository;
import com.modul2.learning.repository.BookRepository;
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
            user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
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
        // Update only the fields provided in the registration/CRUD DTO
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setYearOfBirth(user.getYearOfBirth());
        existingUser.setGender(user.getGender());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setCountry(user.getCountry());
        // Password updates and verification changes could be handled separately
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
