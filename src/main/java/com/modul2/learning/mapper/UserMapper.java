package com.modul2.learning.mapper;

import com.modul2.learning.dto.UserDTO;
import com.modul2.learning.entities.User;
import com.modul2.learning.utils.PasswordUtils;

public class UserMapper {

    public static User userDTO2User(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setYearOfBirth(userDTO.getYearOfBirth());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCountry(userDTO.getCountry());

        // Hash the password before storing
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(PasswordUtils.hashPassword(userDTO.getPassword()));
        }

        // Force verifiedAccount to false on registration
        user.setVerifiedAccount(false);

        return user;
    }

    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setYearOfBirth(user.getYearOfBirth());
        userDTO.setGender(user.getGender());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setCountry(user.getCountry());
        // Do not expose the hashed password in the DTO
        userDTO.setPassword(null);
        userDTO.setVerifiedAccount(user.isVerifiedAccount());
        return userDTO;
    }
}
