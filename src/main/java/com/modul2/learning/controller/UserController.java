package com.modul2.learning.controller;

import com.modul2.learning.dto.UserDTO;
import com.modul2.learning.entities.User;
import com.modul2.learning.mapper.UserMapper;
import com.modul2.learning.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//operatii de tipul CRUD - CREATE, READ, UPDATE, DELETE
@RestController
//folosim forma de plural la endpoints
@RequestMapping("/users")
public class UserController {

    //dependency injection + inversion of control
    //bean - un object manage-uit de SpringBoot
    @Autowired
    private UserService userService;


    // REGISTRATION ENDPOINT
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(user);
        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }
    //VERIFY ENDPOINT
    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam String email, @RequestParam String code) {
        boolean verified = userService.verifyAccount(email, code);
        if (verified) {
            return ResponseEntity.ok("Account verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired verification code");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            User user = userService.login(email, password);
            return ResponseEntity.ok(UserMapper.user2UserDTO(user));
        } catch (EntityNotFoundException | IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }







    //CRUD ENDPOINTS
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User user) {
        //comment daca am avea parametrul ca DTO:
        //pas1: il convertesc intr-o entitate User (printr-o clasa Mapper)
        //pas 2: linia de mai jos
        User createdUser = userService.create(user);
        //pas 3: convertesc entitatea din nou intr-un DTO
        return ResponseEntity.ok(createdUser);
    }


    //returnam un user dupa id
    //id-ul il pun in path/cale, pentru ca un GET nu are request body (doar response body)
    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable(name = "userId") Long userIdToSearchFor) {
        User foundUser = userService.getById(userIdToSearchFor);
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.update(userId, user);
        return ResponseEntity.ok(updatedUser);
    }




}
