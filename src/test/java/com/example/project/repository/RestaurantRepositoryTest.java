package com.example.project.repository;

import com.example.project.controller.NotFoundException;
import com.example.project.entity.Dish;
import com.example.project.entity.Restaurant;
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
class RestaurantRepositoryTest {

    @Autowired
    RestaurantRepository repository;

    @BeforeEach
    void setUp() {
        Restaurant restaurant = new Restaurant(2L, "Name", "Address", List.of());
        repository.save(restaurant);
    }

    @Test
    void findByIdShouldFindRestaurant() {
        Restaurant restaurant = repository.findById(2L).get();

        assertThat(restaurant).hasFieldOrPropertyWithValue("id", restaurant.getId());
        assertThat(restaurant).hasFieldOrPropertyWithValue("name", restaurant.getName());
        assertThat(restaurant).hasFieldOrPropertyWithValue("address", restaurant.getAddress());
        assertThat(restaurant).hasFieldOrPropertyWithValue("menu", restaurant.getMenu());
    }

    @Test
    void findByIdShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            repository.findById(Long.MAX_VALUE).orElseThrow(() -> new NotFoundException("Restaurant not found"));
        });
    }
}