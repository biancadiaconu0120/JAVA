package com.modul2.learning.dto;

public class LibrarianDTO {
    private Long id;
    private String name;
    private String email;
    private String password; // plain text (hashed in service)
    private boolean accountVerified = false;
    private LibraryDTO library;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAccountVerified() { return accountVerified; }
    public void setAccountVerified(boolean accountVerified) { this.accountVerified = accountVerified; }

    public LibraryDTO getLibrary() { return library; }
    public void setLibrary(LibraryDTO library) { this.library = library; }
}
