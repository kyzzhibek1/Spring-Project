package com.example.project.controller;

import com.example.project.dto.DishDTO;
import com.example.project.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping
    public ResponseEntity<Page<DishDTO>> getAllDishes(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<DishDTO> dishes = dishService.getAllDishes(pageable);
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable("id") Long id) {
        DishDTO dish = dishService.getDishById(id);
        return ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<DishDTO> createDish(@Validated @RequestBody DishDTO dishDto) {
        DishDTO dish = dishService.createDish(dishDto);
        return ResponseEntity.ok(dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable("id") Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
