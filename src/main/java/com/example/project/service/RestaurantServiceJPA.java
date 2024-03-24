package com.example.project.service;

import com.example.project.controller.NotFoundException;
import com.example.project.dto.OrderDTO;
import com.example.project.dto.RestaurantDTO;
import com.example.project.entity.Dish;
import com.example.project.entity.Order;
import com.example.project.entity.Restaurant;
import com.example.project.mapper.RestaurantMapper;
import com.example.project.repository.DishRepository;
import com.example.project.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceJPA implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Override
    public Page<RestaurantDTO> getAllRestaurants(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        return restaurants.map(restaurantMapper::restaurantToRestaurantDto);
    }

    @Override
    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.restaurantDtoToRestaurant(restaurantDTO);
        restaurant.setMenu(List.of());
        restaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException());
        restaurantRepository.delete(restaurant);
    }

    @Override
    public RestaurantDTO addDishToMenu(Long restaurantId, Long dishId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Restaurant not found"));
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish not found"));

        restaurant.getMenu().add(dish);
        restaurant = restaurantRepository.save(restaurant);

        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public void deleteDishFromMenu(Long restaurantId, Long dishId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Restaurant not found"));
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish not found"));

        restaurant.getMenu().remove(dish);
        restaurantRepository.save(restaurant);
    }
}