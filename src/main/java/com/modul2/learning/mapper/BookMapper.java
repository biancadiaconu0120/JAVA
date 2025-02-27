package com.modul2.learning.mapper;

import com.modul2.learning.dto.BookDTO;
import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.BookCategory;

public class BookMapper {
    public static Book bookDTO2Book(BookDTO dto) {
        Book book = new Book();
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setAppearanceDate(dto.getAppearanceDate());
        book.setNrOfPages(dto.getNrOfPages());
        // Convert category string to enum
        book.setCategory(BookCategory.valueOf(dto.getCategory().toUpperCase()));
        book.setLanguage(dto.getLanguage());
        // library association will be set in the service method using dto.getLibraryId()
        return book;
    }

    public static BookDTO book2BookDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setAppearanceDate(book.getAppearanceDate());
        dto.setNrOfPages(book.getNrOfPages());
        dto.setCategory(book.getCategory().name());//converts enum in string
        dto.setLanguage(book.getLanguage());
        if (book.getLibrary() != null) {
            dto.setLibraryId(book.getLibrary().getId());
        }
        return dto;
    }
}
