package com.example.project.mapper;

import com.example.project.dto.DishDTO;
import com.example.project.dto.RestaurantDTO;
import com.example.project.entity.Dish;
import com.example.project.entity.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DishMapperTest {

    @Autowired
    DishMapper mapper;

    @Test
    void dishDtoToDish() {
        DishDTO dto = new DishDTO(1L, "Name", "Description", 9.99, new RestaurantDTO());
        Dish dish = mapper.dishDtoToDish(dto);

        assertNotNull(dish);
        assertEquals(dish.getId(), dto.getId());
        assertEquals(dish.getPrice(), dto.getPrice());
        assertEquals(dish.getName(), dto.getName());
    }

    @Test
    void dishToDishDto() {
        Dish dish = new Dish(1L, "Name", "Description", 9.99, new Restaurant());

        DishDTO dto = mapper.dishToDishDto(dish);

        assertNotNull(dto);
        assertEquals(dto.getId(), dish.getId());
        assertEquals(dto.getName(), dish.getName());
        assertEquals(dto.getDescription(), dish.getDescription());
    }
}