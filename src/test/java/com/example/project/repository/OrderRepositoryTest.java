package com.example.project.repository;

import com.example.project.controller.NotFoundException;
import com.example.project.entity.Dish;
import com.example.project.entity.Order;
import com.example.project.entity.User;
import com.example.project.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    OrderRepository repository;

    @BeforeEach
    void setUp() {
        Order order = new Order(2L, new User(), LocalDateTime.now(), List.of(), 9.99, OrderStatus.COMPLETED);
        repository.save(order);
    }

    @Test
    void findByIdShouldFindOrder() {
        Order order = repository.findById(2L).get();

        assertThat(order).hasFieldOrPropertyWithValue("id", order.getId());
        assertThat(order).hasFieldOrPropertyWithValue("user", order.getUser());
        assertThat(order).hasFieldOrPropertyWithValue("orderTime", order.getOrderTime());
        assertThat(order).hasFieldOrPropertyWithValue("dishes", order.getDishes());
        assertThat(order).hasFieldOrPropertyWithValue("totalCost", order.getTotalCost());
        assertThat(order).hasFieldOrPropertyWithValue("status", order.getStatus());

    }

    @Test
    void findByIdShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            repository.findById(Long.MAX_VALUE).orElseThrow(() -> new NotFoundException("Order not found"));
        });
    }
}