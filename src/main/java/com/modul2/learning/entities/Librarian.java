package com.modul2.learning.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "librarian", schema = "public")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", unique = true, nullable = false) //am pus unic ca sa nu avem 2 cu acelasi email
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACCOUNT_VERIFIED")
    private boolean accountVerified = false; //false initial

    // OneToOne with Library (fiecare Librarian are un Library)
    @OneToOne(cascade = CascadeType.ALL)//persist,merge,remove sunt incluse in ALL
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase().trim(); //pt login desi nu merge?????
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
