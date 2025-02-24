package com.modul2.learning.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "library", schema = "public")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name="ADDRESS", nullable=false)
    private String address;

    @Column(name="PHONE_NUMBER", nullable=false)
    private String phoneNumber;

    //OneToOne with Librarian
    @OneToOne(mappedBy = "library", cascade = CascadeType.ALL)
    private Librarian librarian;

    // OneToMany relationship with Book
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<Book> books;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Librarian getLibrarian() { return librarian; }
    public void setLibrarian(Librarian librarian) { this.librarian = librarian; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
