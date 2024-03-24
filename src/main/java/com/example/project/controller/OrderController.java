package com.example.project.controller;

import com.example.project.dto.OrderDTO;
import com.example.project.entity.Dish;
import com.example.project.entity.Order;
import com.example.project.enums.OrderStatus;
import com.example.project.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Order Controller",
        description = "Controller for operations with orders"
)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "Getting page of orders")
    public ResponseEntity<Page<OrderDTO>> getAllOrders(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<OrderDTO> orders = orderService.getAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting order by id")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    @Operation(summary = "Updates orders by id")
    public ResponseEntity<OrderDTO> createOrder(@Validated @RequestBody OrderDTO orderDto) {
        OrderDTO order = orderService.createOrder(orderDto);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes order by id")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cost")
    @Operation(summary = "Updates cost of order with id")
    public ResponseEntity<OrderDTO> changeOrderTotalCost(@PathVariable("id") Long id, @RequestBody Double cost) {
        OrderDTO order = orderService.changeOrderTotalCost(id, cost);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{id}/dishes")
    @Operation(summary = "Updates dish list of order with id")
    public ResponseEntity<OrderDTO> changeOrderDishesList(@PathVariable("id") Long id, @RequestBody List<Dish> dishes) {
        OrderDTO order = orderService.changeOrderDishesList(id, dishes);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Updates status of order with id")
    public ResponseEntity<OrderDTO> changeOrderStatus(@PathVariable("id") Long id, @RequestBody OrderStatus status) {
        OrderDTO order = orderService.changeOrderStatus(id, status);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{order-id}/add-dish/{dish-id}")
    @Operation(summary = "Adds dish to order by their id")
    public ResponseEntity<OrderDTO> addDishToOrder (@PathVariable("order-id") Long orderId, @PathVariable("dish-id") Long dishId) {
        OrderDTO order = orderService.addDishToOrder(orderId, dishId);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{order-id}/remove-dish/{dish-id}")
    @Operation(summary = "Deleting dish to order by their id")
    public ResponseEntity<Void> removeDishFromOrder (@PathVariable("order-id") Long orderId, @PathVariable("dish-id") Long dishId) {
        orderService.removeDishFromOrder(orderId, dishId);
        return ResponseEntity.noContent().build();
    }
}
