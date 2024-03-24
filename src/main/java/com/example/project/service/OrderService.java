package com.example.project.service;

import com.example.project.dto.OrderDTO;
import com.example.project.entity.Dish;
import com.example.project.entity.Order;
import com.example.project.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Page<OrderDTO> getAllOrders(Pageable pageable);

    OrderDTO getOrderById(Long id);

    OrderDTO createOrder(OrderDTO orderDto);

    OrderDTO changeOrderTotalCost(Long id, Double cost);

    OrderDTO changeOrderDishesList(Long id, List<Dish> dishList);

    OrderDTO changeOrderStatus(Long id, OrderStatus status);

    void deleteOrder(Long id);

    OrderDTO addDishToOrder(Long orderId, Long dishId);

    void removeDishFromOrder(Long orderId, Long dishId);
}