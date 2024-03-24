package com.example.project.mapper;

import com.example.project.dto.DishDTO;
import com.example.project.entity.Dish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMapper {
    Dish dishDtoToDish(DishDTO dishDTO);

    DishDTO dishToDishDto(Dish dish);
}
