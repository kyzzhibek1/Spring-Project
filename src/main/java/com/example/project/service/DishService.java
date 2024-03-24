package com.example.project.service;

import com.example.project.dto.DishDTO;
import com.example.project.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DishService {
    Page<DishDTO> getAllDishes(Pageable pageable);

    DishDTO getDishById(Long id);

    DishDTO createDish(Dish dish);

    void deleteDish(Long id);
}
