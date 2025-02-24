package com.modul2.learning.dto;

import java.time.LocalDate;

public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private LocalDate appearanceDate;
    private int nrOfPages;
    private String category; // We'll use String for the enum value
    private String language;
    private Long libraryId; // optional: library id association

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

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public Long getLibraryId() { return libraryId; }
    public void setLibraryId(Long libraryId) { this.libraryId = libraryId; }
}
