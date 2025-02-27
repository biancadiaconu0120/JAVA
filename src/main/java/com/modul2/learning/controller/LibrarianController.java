package com.modul2.learning.controller;

import com.modul2.learning.dto.LibrarianDTO;

import com.modul2.learning.entities.Librarian;
import com.modul2.learning.mapper.LibrarianMapper;
import com.modul2.learning.service.LibrarianService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;


    @PostMapping("/register")
    public ResponseEntity<?> registerLibrarian(@RequestBody LibrarianDTO librarianDTO) {
        Librarian librarian = LibrarianMapper.librarianDTO2Librarian(librarianDTO);
        Librarian saved = librarianService.register(librarian);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(saved));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            Librarian librarian = librarianService.login(email, password);
            return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(librarian));
        } catch (EntityNotFoundException | IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    //sa fac cu request body si loginul dar sa nu am DTO special pt login


    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String email, @RequestParam String code) {
        boolean verified = librarianService.verifyAccount(email, code);
        if (verified) {
            return ResponseEntity.ok("Account verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired verification code.");
        }
    }
}
