package com.modul2.learning.dto;

import java.util.List;

public class LibraryDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<BookDTO> books;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public List<BookDTO> getBooks() {
        return books;
    }
    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

}
