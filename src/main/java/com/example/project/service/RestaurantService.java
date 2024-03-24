package com.example.project.service;

import com.example.project.dto.RestaurantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {
    Page<RestaurantDTO> getAllRestaurants(Pageable pageable);

    RestaurantDTO getRestaurantById(Long id);

    RestaurantDTO createRestaurant(RestaurantDTO restaurant);

    void deleteRestaurant(Long id);
}
