package com.modul2.learning.mapper;

import com.modul2.learning.dto.UserDTO;
import com.modul2.learning.entities.Application;
import com.modul2.learning.entities.User;

import java.util.List;

public class UserMapper {
    public static User userDTO2User(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setUserName(userDTO.getUserName());
        List<Application> applications = userDTO.getApplications().stream()
//                .map(applicationDto -> ApplicationMapper.applicationDTO2Application(applicationDto))
                .map(ApplicationMapper::applicationDTO2Application)
                .toList();
        user.setApplications(applications);
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
        //ideal: trebuia sa fie si books aici
        return userDTO;
    }
}
