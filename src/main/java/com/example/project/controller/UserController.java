package com.example.project.controller;

import com.example.project.dto.OrderDTO;
import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(
        name = "User Controller",
        description = "Controller for operations with user"
)
public class UserController {

    @Autowired
    private UserService userService;

    
    @GetMapping
    @Operation(summary = "Getting page of users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<UserDTO> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Getting user by id")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Creates user")
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO userDto) {
        UserDTO user = userService.createUser(userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes of users")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/orders")
    @Operation(summary = "Getting order page of user by its id")
    public ResponseEntity<Page<OrderDTO>> getUserOrders(@PathVariable("id") Long id, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<OrderDTO> dtoPage = userService.getUserOrders(id, pageable);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}/order/{order-id}")
    @Operation(summary = "Getting order of user by their id")
    public ResponseEntity<OrderDTO> getUserOrderById(@PathVariable("id") Long id, @PathVariable("order-id") Long orderId) {
        OrderDTO dto = userService.getUserOrderById(id, orderId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/order/add")
    @Operation(summary = "Adds new order to user by its id")
    public ResponseEntity<UserDTO> addUserOrder(@PathVariable("id") Long id, @RequestBody OrderDTO dto) {
        UserDTO userDto = userService.addUserOrder(id, dto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}/order/delete/{order-id}")
    @Operation(summary = "Deletes order to user by their id")
    public ResponseEntity<Void> deleteUserOrder(@PathVariable("id") Long userId, @PathVariable("order-id") Long orderId) {
        userService.deleteUserOrder(userId, orderId);
        return ResponseEntity.noContent().build();
    }
}
