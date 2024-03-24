package com.example.project.repository;

import com.example.project.controller.NotFoundException;
import com.example.project.entity.Restaurant;
import com.example.project.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @BeforeEach
    void setUp() {
        User user = new User(2L, "Name", "Surname", "Password", "+996555000000", List.of());
        repository.save(user);
    }

    @Test
    void findByIdShouldFindUser() {
        User user = repository.findById(2L).get();

        assertThat(user).hasFieldOrPropertyWithValue("id", user.getId());
        assertThat(user).hasFieldOrPropertyWithValue("firstName", user.getFirstName());
        assertThat(user).hasFieldOrPropertyWithValue("lastName", user.getLastName());
        assertThat(user).hasFieldOrPropertyWithValue("password", user.getPassword());
        assertThat(user).hasFieldOrPropertyWithValue("phoneNumber", user.getPhoneNumber());
        assertThat(user).hasFieldOrPropertyWithValue("orders", user.getOrders());
    }

    @Test
    void findByIdShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            repository.findById(Long.MAX_VALUE).orElseThrow(() -> new NotFoundException("User not found"));
        });
    }
}