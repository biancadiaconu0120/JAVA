package com.modul2.learning.mapper;

import com.modul2.learning.dto.UserDTO;
import com.modul2.learning.entities.Application;
import com.modul2.learning.entities.User;
import com.modul2.learning.entities.Book;

import java.util.List;

public class UserMapper {
    public static User userDTO2User(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setUserName(userDTO.getUserName());

        List<Application> applications = userDTO.getApplications().stream()
                .map(ApplicationMapper::applicationDTO2Application)
                .toList();
        user.setApplications(applications);

        List<Book> books = userDTO.getBooks().stream()
                .map(BookMapper::bookDto2Book)
                .peek(book -> book.setUser(user)) // Maintain bidirectional link
                .toList();
        user.setBooks(books);

        return user;
    }

    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setUserName(user.getUserName());

        userDTO.setApplications(user.getApplications().stream()
                .map(ApplicationMapper::application2ApplicationDTO)
                .toList());

        userDTO.setBooks(user.getBooks().stream()
                .map(BookMapper::book2BookDto)
                .toList());

        return userDTO;
    }
}
