package com.modul2.learning.mapper;

import com.modul2.learning.dto.BookDTO;
import com.modul2.learning.entities.Book;

public class BookMapper {
    public static BookDTO book2BookDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthor(book.getAuthor());
        return bookDTO;
    }

    public static Book bookDto2Book(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        return book;
    }
}
