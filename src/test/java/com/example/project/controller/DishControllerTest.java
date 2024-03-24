package com.example.project.controller;

import com.example.project.controller.DishController;
import com.example.project.dto.DishDTO;
import com.example.project.service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishControllerTest {

    @Mock
    private DishService dishService;

    @InjectMocks
    private DishController dishController;

    @Test
    void testGetAllDishes() {
        Pageable pageable = Pageable.unpaged();
        Page<DishDTO> expectedPage = new PageImpl<>(Collections.emptyList());
        when(dishService.getAllDishes(pageable)).thenReturn(expectedPage);

        ResponseEntity<Page<DishDTO>> responseEntity = dishController.getAllDishes(pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedPage, responseEntity.getBody());
    }

    @Test
    void testGetDishById() {
        DishDTO expectedDish = new DishDTO();
        when(dishService.getDishById(1L)).thenReturn(expectedDish);

        ResponseEntity<DishDTO> responseEntity = dishController.getDishById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDish, responseEntity.getBody());
    }

    @Test
    void testCreateDish() {
        DishDTO dishDTO = new DishDTO();
        when(dishService.createDish(dishDTO)).thenReturn(dishDTO);

        ResponseEntity<DishDTO> responseEntity = dishController.createDish(dishDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dishDTO, responseEntity.getBody());
    }

    @Test
    void testDeleteDish() {
        ResponseEntity<Void> responseEntity = dishController.deleteDish(1L);

        verify(dishService, times(1)).deleteDish(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
