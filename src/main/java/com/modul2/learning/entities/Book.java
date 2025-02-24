package com.modul2.learning.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book", schema = "public")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ISBN", nullable=false, unique=true)
    private String isbn;

    @Column(name="TITLE", nullable=false)
    private String title;

    @Column(name="AUTHOR", nullable=false)
    private String author;

    @Column(name="APPEARANCE_DATE", nullable=false)
    private LocalDate appearanceDate;

    @Column(name="NR_OF_PAGES", nullable=false)
    private int nrOfPages;

    @Enumerated(EnumType.STRING)
    @Column(name="CATEGORY", nullable=false)
    private BookCategory category;

    @Column(name="LANGUAGE", nullable=false)
    private String language;

    // Many books belong to one library
    @ManyToOne
    @JoinColumn(name = "library_id", nullable=false)
    private Library library;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDate getAppearanceDate() { return appearanceDate; }
    public void setAppearanceDate(LocalDate appearanceDate) { this.appearanceDate = appearanceDate; }

    public int getNrOfPages() { return nrOfPages; }
    public void setNrOfPages(int nrOfPages) { this.nrOfPages = nrOfPages; }

    public BookCategory getCategory() { return category; }
    public void setCategory(BookCategory category) { this.category = category; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public Library getLibrary() { return library; }
    public void setLibrary(Library library) { this.library = library; }
}

