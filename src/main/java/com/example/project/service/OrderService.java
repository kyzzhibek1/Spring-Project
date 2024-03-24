package com.example.project.service;

import com.example.project.dto.OrderDTO;
import com.example.project.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderDTO> getAllOrders(Pageable pageable);

    OrderDTO getOrderById(Long id);

    OrderDTO createOrder(OrderDTO orderDto);

    void deleteOrder(Long id);
}
