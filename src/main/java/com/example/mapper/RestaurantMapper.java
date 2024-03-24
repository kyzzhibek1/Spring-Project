package com.example.mapper;

import com.example.dto.RestaurantDTO;
import com.example.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant restaurantDtoToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO  restaurantToRestaurantDto(Restaurant restaurant);
}
