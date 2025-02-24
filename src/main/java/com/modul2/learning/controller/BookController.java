package com.modul2.learning.controller;

import com.modul2.learning.dto.BookDTO;
import com.modul2.learning.entities.Book;
import com.modul2.learning.mapper.BookMapper;
import com.modul2.learning.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Add a book to a library (libraryId in path)
    @PostMapping("/library/{libraryId}")
    public ResponseEntity<?> addBookToLibrary(@PathVariable Long libraryId, @RequestBody BookDTO bookDTO) {
        Book book = BookMapper.bookDTO2Book(bookDTO);
        Book savedBook = bookService.addBookToLibrary(book, libraryId);
        return ResponseEntity.ok(BookMapper.book2BookDTO(savedBook));
    }

    // Remove a book from a library
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> removeBook(@PathVariable Long bookId) {
        bookService.removeBookFromLibrary(bookId);
        return ResponseEntity.ok("Book removed successfully");
    }

    // Edit a book
    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        Book book = BookMapper.bookDTO2Book(bookDTO);
        Book updatedBook = bookService.updateBook(bookId, book);
        return ResponseEntity.ok(BookMapper.book2BookDTO(updatedBook));
    }

    // Get a single book by id
    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable Long bookId) {
        Book book = bookService.getBook(bookId);
        return ResponseEntity.ok(BookMapper.book2BookDTO(book));
    }

    // List all books
    @GetMapping
    public ResponseEntity<?> listBooks() {
        List<Book> books = bookService.listBooks();
        return ResponseEntity.ok(books.stream().map(BookMapper::book2BookDTO).toList());
    }

    // List books with pagination
    @GetMapping("/paginated")
    public ResponseEntity<?> listBooksPaginated(@RequestParam int page, @RequestParam int size) {
        Page<Book> bookPage = bookService.listBooksPaginated(page, size);
        return ResponseEntity.ok(bookPage.map(BookMapper::book2BookDTO));
    }
}
