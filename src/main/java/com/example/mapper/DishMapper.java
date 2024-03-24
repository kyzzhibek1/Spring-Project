package com.example.mapper;

import com.example.dto.DishDTO;
import com.example.entity.Dish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMapper {
    Dish dishDtoToDish(DishDTO dishDTO);

    DishDTO dishToDishDto(Dish dish);
}
