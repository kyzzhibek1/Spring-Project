package com.example.project.mapper;

import com.example.project.dto.RestaurantDTO;
import com.example.project.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant restaurantDtoToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO  restaurantToRestaurantDto(Restaurant restaurant);
}
