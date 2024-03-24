package com.example.project.service;

import com.example.project.dto.DishDTO;
import com.example.project.entity.Dish;
import com.example.project.mapper.DishMapper;
import com.example.project.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DishServiceJPA implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishMapper dishMapper;

    @Override
    public Page<DishDTO> getAllDishes(Pageable pageable) {
        Page<Dish> dishes = dishRepository.findAll(pageable);
        return dishes.map(dishMapper::dishToDishDto);
    }

    @Override
    public DishDTO getDishById(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return dishMapper.dishToDishDto(dish);
    }

    @Override
    public DishDTO createDish(Dish dish) {
        dish = dishRepository.save(dish);
        return dishMapper.dishToDishDto(dish);
    }

    @Override
    public void deleteDish(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new RuntimeException());
        dishRepository.delete(dish);
    }
}