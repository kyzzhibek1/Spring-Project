package com.example.service;

import com.example.dto.OrderDTO;
import com.example.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderDTO> getAllOrders(Pageable pageable);

    OrderDTO getOrderById(Long id);

    OrderDTO createOrder(Order order);

    void deleteOrder(Long id);
}