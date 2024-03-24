package com.example.project.controller;

import com.example.project.dto.OrderDTO;
import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetAllUsers() {
        Page<UserDTO> usersPage = new PageImpl<>(List.of());
        when(userService.getAllUsers(any(Pageable.class))).thenReturn(usersPage);

        ResponseEntity<Page<UserDTO>> result = userController.getAllUsers(PageRequest.of(0, 10));

        assertEquals(usersPage, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).getAllUsers(any(Pageable.class));
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO(userId, "John", "Doe", "1234567890", List.of());
        when(userService.getUserById(userId)).thenReturn(userDTO);

        ResponseEntity<UserDTO> result = userController.getUserById(userId);

        assertEquals(userDTO, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testCreateUser() {
        UserDTO userDTO = new UserDTO(1L, "John", "Doe", "1234567890", List.of());
        when(userService.createUser(userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> result = userController.createUser(userDTO);

        assertEquals(userDTO, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).createUser(userDTO);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<Void> result = userController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void testGetUserOrders() {
        Long userId = 1L;
        Page<OrderDTO> ordersPage = new PageImpl<>(Collections.emptyList());
        when(userService.getUserOrders(userId, Pageable.unpaged())).thenReturn(ordersPage);

        ResponseEntity<Page<OrderDTO>> result = userController.getUserOrders(userId, Pageable.unpaged());

        assertEquals(ordersPage, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).getUserOrders(userId, Pageable.unpaged());
    }

    @Test
    void testGetUserOrderById() {
        Long userId = 1L;
        Long orderId = 2L;
        OrderDTO orderDTO = new OrderDTO(orderId, null, null, null, 0.0, null);
        when(userService.getUserOrderById(userId, orderId)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> result = userController.getUserOrderById(userId, orderId);

        assertEquals(orderDTO, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).getUserOrderById(userId, orderId);
    }

    @Test
    void testAddUserOrder() {
        long userId = 1L;
        OrderDTO orderDTO = new OrderDTO(1L, null, null, null, 0.0, null);
        UserDTO userDTO = new UserDTO(userId, "John", "Doe", "1234567890", List.of());
        when(userService.addUserOrder(userId, orderDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> result = userController.addUserOrder(userId, orderDTO);

        assertEquals(userDTO, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).addUserOrder(userId, orderDTO);
    }

    @Test
    void testDeleteUserOrder() {
        Long userId = 1L;
        Long orderId = 2L;

        ResponseEntity<Void> result = userController.deleteUserOrder(userId, orderId);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(userService, times(1)).deleteUserOrder(userId, orderId);
    }
}
