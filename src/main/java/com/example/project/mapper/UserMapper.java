package com.example.project.mapper;

import com.example.project.dto.AuthRegisterDTO;
import com.example.project.dto.UserDTO;
import com.example.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authorities", ignore = true)
    User userDtoToUser(UserDTO userDTO);

    UserDTO userToUserDto(User user);

    @Mapping(target = "authorities", ignore = true)
    User authDtoToUser(AuthRegisterDTO authRegisterDTO);

}
