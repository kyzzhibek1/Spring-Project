package com.example.project.repository;

import com.example.project.controller.NotFoundException;
import com.example.project.entity.Dish;
import com.example.project.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DishRepositoryTest {

    @Autowired
    DishRepository repository;

    @BeforeEach
    void setUp() {
        Dish dish = new Dish(10L, "Name1", "Description1", 9.99, new Restaurant());
        repository.save(dish);

        Dish dish2 = new Dish(15L, "Name2", "Description2", 19.99, new Restaurant());
        repository.save(dish2);
    }

    @Test
    void findByIdShouldFindDish() {
        Dish dish = repository.findById(1L).get();

        assertThat(dish).hasFieldOrPropertyWithValue("id", dish.getId());
        assertThat(dish).hasFieldOrPropertyWithValue("name", dish.getName());
        assertThat(dish).hasFieldOrPropertyWithValue("description", dish.getDescription());
        assertThat(dish).hasFieldOrPropertyWithValue("price", dish.getPrice());
        assertThat(dish).hasFieldOrPropertyWithValue("restaurant", dish.getRestaurant());
    }

    @Test
    void findByIdShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            repository.findById(Long.MAX_VALUE).orElseThrow(() -> new NotFoundException("Dish not found"));
        });
    }
}