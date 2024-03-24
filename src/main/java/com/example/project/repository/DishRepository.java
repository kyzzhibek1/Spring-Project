package com.example.project.repository;

import com.example.project.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
    Page<Dish> findAll(Pageable pageable);
}
