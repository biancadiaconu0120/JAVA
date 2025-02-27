package com.modul2.learning.service;

import com.modul2.learning.entities.Librarian;
import com.modul2.learning.repository.LibrarianRepository;
import com.modul2.learning.utils.PasswordUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private EmailService emailService;

    // Register Librarian
    public Librarian register(Librarian librarian) {
        if (librarian.getPassword() != null && !librarian.getPassword().isEmpty()) {
            librarian.setPassword(PasswordUtils.hashPassword(librarian.getPassword().trim()));
        }
        librarian.setAccountVerified(false);
        Librarian saved = librarianRepository.save(librarian);
        try {
            emailService.sendVerificationEmail(librarian.getEmail());
        } catch (Exception ex) {
            throw new RuntimeException("Librarian registered but failed to send verification email", ex);
        }
        return saved;
    }

    // Login Librarian
    public Librarian login(String email, String password) {
        String normalizedEmail = email.toLowerCase().trim();
        Librarian librarian = librarianRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found for email: " + normalizedEmail));
        String hashedInput = PasswordUtils.hashPassword(password.trim());
        if (!librarian.getPassword().equals(hashedInput)) {
            throw new EntityNotFoundException("Invalid credentials");
        }
        if (!librarian.isAccountVerified()) {
            throw new EntityNotFoundException("Account is not verified");
        }
        return librarian;
    }

    // Verify Librarian account
    public boolean verifyAccount(String email, String code) {
        boolean valid = emailService.verifyUser(email, code);
        if (valid) {
            Librarian librarian = librarianRepository.findByEmail(email.toLowerCase().trim())
                    .orElseThrow(() -> new EntityNotFoundException("Librarian not found"));

            librarian.setAccountVerified(true);
            librarianRepository.save(librarian);
            return true;
        }
        return false;
    }
}
