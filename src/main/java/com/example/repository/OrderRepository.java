package com.example.repository;

import com.example.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Page<Order> findAll(Pageable pageable);
}
