package com.example.project.controller;

import com.example.project.dto.OrderDTO;
import com.example.project.entity.Dish;
import com.example.project.entity.Order;
import com.example.project.enums.OrderStatus;
import com.example.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderDTO>> getAllOrders(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<OrderDTO> orders = orderService.getAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Validated @RequestBody OrderDTO orderDto) {
        OrderDTO order = orderService.createOrder(orderDto);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cost")
    public ResponseEntity<OrderDTO> changeOrderTotalCost(@PathVariable("id") Long id, @RequestBody Double cost) {
        OrderDTO order = orderService.changeOrderTotalCost(id, cost);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{id}/dishes")
    public ResponseEntity<OrderDTO> changeOrderDishesList(@PathVariable("id") Long id, @RequestBody List<Dish> dishes) {
        OrderDTO order = orderService.changeOrderDishesList(id, dishes);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDTO> changeOrderStatus(@PathVariable("id") Long id, @RequestBody OrderStatus status) {
        OrderDTO order = orderService.changeOrderStatus(id, status);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{order-id}/add-dish/{dish-id}")
    public ResponseEntity<OrderDTO> addDishToOrder (@PathVariable("order-id") Long orderId, @PathVariable("dish-id") Long dishId) {
        OrderDTO order = orderService.addDishToOrder(orderId, dishId);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{order-id}/remove-dish/{dish-id}")
    public ResponseEntity<Void> removeDishFromOrder (@PathVariable("order-id") Long orderId, @PathVariable("dish-id") Long dishId) {
        orderService.removeDishFromOrder(orderId, dishId);
        return ResponseEntity.noContent().build();
    }
}
