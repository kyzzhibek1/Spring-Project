package com.example.project.controller;

import com.example.project.dto.RestaurantDTO;
import com.example.project.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    RestaurantService restaurantService;

    @InjectMocks
    RestaurantController restaurantController;

    @Test
    void getAllRestaurants() {
        Pageable pageable = PageRequest.of(0, 10);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        Page<RestaurantDTO> restaurantDTOPage = new PageImpl<>(Collections.singletonList(restaurantDTO));
        when(restaurantService.getAllRestaurants(pageable)).thenReturn(restaurantDTOPage);

        ResponseEntity<Page<RestaurantDTO>> responseEntity = restaurantController.getAllRestaurants(pageable);

        assertEquals(1, responseEntity.getBody().getTotalElements());
        verify(restaurantService, times(1)).getAllRestaurants(pageable);
    }

    @Test
    void getRestaurantById() {
        Long id = 1L;
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        when(restaurantService.getRestaurantById(id)).thenReturn(restaurantDTO);

        ResponseEntity<RestaurantDTO> responseEntity = restaurantController.getRestaurantById(id);

        assertEquals(restaurantDTO, responseEntity.getBody());
        verify(restaurantService, times(1)).getRestaurantById(id);
    }

    @Test
    void createRestaurant() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        when(restaurantService.createRestaurant(restaurantDTO)).thenReturn(restaurantDTO);

        ResponseEntity<RestaurantDTO> responseEntity = restaurantController.createRestaurant(restaurantDTO);

        assertEquals(restaurantDTO, responseEntity.getBody());
        verify(restaurantService, times(1)).createRestaurant(restaurantDTO);
    }

    @Test
    void deleteRestaurant() {
        Long id = 1L;
        ResponseEntity<Void> responseEntity = restaurantController.deleteRestaurant(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(restaurantService, times(1)).deleteRestaurant(id);
    }

    @Test
    void addDishToMenu() {
        Long restaurantId = 1L;
        Long dishId = 2L;
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        when(restaurantService.addDishToMenu(restaurantId, dishId)).thenReturn(restaurantDTO);

        ResponseEntity<RestaurantDTO> responseEntity = restaurantController.addDishToMenu(restaurantId, dishId);

        assertEquals(restaurantDTO, responseEntity.getBody());
        verify(restaurantService, times(1)).addDishToMenu(restaurantId, dishId);
    }

    @Test
    void deleteDishFromMenu() {
        Long restaurantId = 1L;
        Long dishId = 2L;
        ResponseEntity<Void> responseEntity = restaurantController.deleteDishFromMenu(restaurantId, dishId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(restaurantService, times(1)).deleteDishFromMenu(restaurantId, dishId);
    }
}