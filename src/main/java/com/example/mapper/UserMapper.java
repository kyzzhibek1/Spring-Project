package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDTO userDTO);

    UserDTO userToUserDto(User user);
}
