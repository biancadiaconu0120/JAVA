package com.modul2.learning.service;

import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.Library;
import com.modul2.learning.repository.BookRepository;
import com.modul2.learning.repository.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    // Add a book to a library
    public Book addBookToLibrary(Book book, Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new EntityNotFoundException("Library not found"));
        book.setLibrary(library);
        return bookRepository.save(book);
    }

    // Remove a book from a library
    public void removeBookFromLibrary(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new EntityNotFoundException("Book not found");
        }
        bookRepository.deleteById(bookId);
    }

    // Edit a book
    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setAppearanceDate(updatedBook.getAppearanceDate());
        existingBook.setNrOfPages(updatedBook.getNrOfPages());
        existingBook.setCategory(updatedBook.getCategory());
        existingBook.setLanguage(updatedBook.getLanguage());
        // Optionally, update the library association if required
        return bookRepository.save(existingBook);
    }

    // Get a book
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    // List all books
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    // List books paginated
    public Page<Book> listBooksPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return bookRepository.findAll(pageable);
    }
}
