package com.modul2.learning.service;

import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.User;
import com.modul2.learning.repository.BookRepository;
import com.modul2.learning.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public Book create(Book bookToCreate, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        //varianta comentata ar fi fost fara metoda addBook() in User, unde am setat
        //relatia bidirectional
        //user.getBooks().add(bookToCreate);
        //bookToCreate.setUser(user);
        user.addBook(bookToCreate);
        bookRepository.save(bookToCreate);
        return bookToCreate;
    }
}
