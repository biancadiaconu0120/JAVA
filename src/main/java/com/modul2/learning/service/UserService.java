package com.modul2.learning.service;

import com.modul2.learning.entities.Application;
import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.User;
import com.modul2.learning.repository.ApplicationRepository;
import com.modul2.learning.repository.BookRepository;
import com.modul2.learning.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//clasa de business logic (un serviciu pe care aplicatia il ofera)
@Service
//@Component
public class UserService {
    @Autowired
    private UserRepository userRepository; //Injects UserRepository, which handles database operations.
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public User create(User user) {
        if (user.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new user that you want to create");
        }
        return userRepository.save(user);
    }

    public User getById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        existingUser.setUserName(user.getUserName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAge(user.getAge());

        return userRepository.save(existingUser);
    }

    public void delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    public User addBookToUser(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        user.addBook(book);  // Add book to the user's list
        userRepository.save(user);  // Save the updated user
        return user;
    }


    public User addApplicationToUser(Long userId, Long applicationId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));

        user.getApplications().add(application);  // Add application to user's list
        userRepository.save(user);  // Save the updated user
        return user;
    }

    public User removeBookFromUser(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        user.getBooks().remove(book);  // Remove book from list
        userRepository.save(user);  // Save user (triggers orphan removal)

        return user;
    }


}
