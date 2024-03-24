package com.example.project.service;

import com.example.project.controller.NotFoundException;
import com.example.project.dto.OrderDTO;
import com.example.project.entity.Dish;
import com.example.project.entity.Order;
import com.example.project.enums.OrderStatus;
import com.example.project.mapper.OrderMapper;
import com.example.project.repository.DishRepository;
import com.example.project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceJPA implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::orderToOrderDto);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        order.setOrderTime(LocalDateTime.now());
        order.setDishes(List.of());
        order.setStatus(OrderStatus.IN_PROGRESS);
        orderDto = orderMapper.orderToOrderDto(orderRepository.save(orderMapper.orderDtoToOrder(orderDto)));
        return orderDto;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public OrderDTO changeOrderTotalCost(Long id, Double cost) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setTotalCost(cost);
        order = orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO changeOrderDishesList(Long id, List<Dish> dishList) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setDishes(dishList);
        order = orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO changeOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(status);
        order = orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO addDishToOrder(Long orderId, Long dishId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish not found"));

        order.getDishes().add(dish);
        order = orderRepository.save(order);

        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public void removeDishFromOrder(Long orderId, Long dishId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish not found"));

        order.getDishes().remove(dish);
        order = orderRepository.save(order);
    }
}
