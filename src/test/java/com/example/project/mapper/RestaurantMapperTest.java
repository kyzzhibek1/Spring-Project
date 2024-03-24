package com.example.project.mapper;

import com.example.project.dto.RestaurantDTO;
import com.example.project.entity.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantMapperTest {

    @Autowired
    RestaurantMapper mapper;

    @Test
    void restaurantDtoToRestaurant() {
        RestaurantDTO dto = new RestaurantDTO(1L, "Name", "Address", List.of());

        Restaurant restaurant = mapper.restaurantDtoToRestaurant(dto);

        assertNotNull(restaurant);
        assertEquals(dto.getId(), restaurant.getId());
        assertEquals(dto.getName(), restaurant.getName());
        assertEquals(dto.getAddress(), restaurant.getAddress());
    }

    @Test
    void restaurantToRestaurantDto() {
        Restaurant restaurant = new Restaurant(1L, "Name", "Address", List.of());

        RestaurantDTO dto = mapper.restaurantToRestaurantDto(restaurant);

        assertNotNull(dto);
        assertEquals(restaurant.getId(), dto.getId());
        assertEquals(restaurant.getName(), dto.getName());
        assertEquals(restaurant.getAddress(), dto.getAddress());
    }
}