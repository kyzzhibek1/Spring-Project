package com.example.project.service;

import com.example.project.dto.OrderDTO;
import com.example.project.entity.Order;
import com.example.project.mapper.OrderMapper;
import com.example.project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceJPA implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::orderToOrderDto);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDto) {
        orderDto = orderMapper.orderToOrderDto(orderRepository.save(orderMapper.orderDtoToOrder(orderDto)));
        return orderDto;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException());
        orderRepository.delete(order);
    }
}
