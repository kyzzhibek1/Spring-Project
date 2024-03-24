package com.example.project.controller;

import com.example.project.dto.RestaurantDTO;
import com.example.project.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<Page<RestaurantDTO>> getAllRestaurants(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<RestaurantDTO> restaurants = restaurantService.getAllRestaurants(pageable);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable("id") Long id) {
        RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Validated @RequestBody RestaurantDTO restaurantDto) {
        RestaurantDTO restaurant = restaurantService.createRestaurant(restaurantDto);
        return ResponseEntity.ok(restaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{restaurant-id}/add-dish/{dish-id}")
    public ResponseEntity<RestaurantDTO> addDishToMenu(@PathVariable("restaurant-id") Long restaurantId, @PathVariable("dish-id") Long dishId) {
        RestaurantDTO restaurantDTO = restaurantService.addDishToMenu(restaurantId, dishId);
        return ResponseEntity.ok(restaurantDTO);
    }

    @DeleteMapping("/{restaurant-id}/delete-dish/{dish-id}")
    public ResponseEntity<Void> deleteDishFromMenu(@PathVariable("restaurant-id") Long restaurantId, @PathVariable("dish-id") Long dishId) {
        restaurantService.deleteDishFromMenu(restaurantId, dishId);
        return ResponseEntity.noContent().build();
    }
}