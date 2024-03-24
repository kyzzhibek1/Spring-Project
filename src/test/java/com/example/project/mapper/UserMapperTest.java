package com.example.project.mapper;

import com.example.project.dto.UserDTO;
import com.example.project.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Test
    void userDtoToUser() {
        UserDTO dto = new UserDTO(1L, "Name", "Surname", "1234567890", null);

        User user = mapper.userDtoToUser(dto);

        assertNotNull(user);
        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getFirstName(), user.getFirstName());
        assertEquals(dto.getLastName(), user.getLastName());
        assertEquals(dto.getPhoneNumber(), user.getPhoneNumber());
    }

    @Test
    void userToUserDto() {
        User user = new User(1L, "Name", "Doe", "Surname", "Password", null);

        UserDTO dto = mapper.userToUserDto(user);

        assertNotNull(dto);
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getLastName(), dto.getLastName());
        assertEquals(user.getPhoneNumber(), dto.getPhoneNumber());
    }
}