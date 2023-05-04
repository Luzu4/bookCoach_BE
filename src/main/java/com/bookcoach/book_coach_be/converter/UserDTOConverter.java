package com.bookcoach.book_coach_be.converter;

import com.bookcoach.book_coach_be.dto.UserDTO;
import com.bookcoach.book_coach_be.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Primary
@Component
public class UserDTOConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNickName(user.getNickName());
        userDTO.setRole(user.getRole());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setUserDetailsAll(user.getUserDetails());
        return userDTO;
    }
}
