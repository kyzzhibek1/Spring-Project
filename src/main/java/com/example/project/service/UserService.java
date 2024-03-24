package com.example.project.service;

import com.example.project.dto.OrderDTO;
import com.example.project.dto.UserDTO;
import com.example.project.entity.Order;
import com.example.project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> getAllUsers(Pageable pageable);

    UserDTO getUserById(Long id);

    UserDTO createUser(UserDTO userDto);

    void deleteUser(Long id);

    Page<OrderDTO> getUserOrders(Long id, Pageable pageable);

    OrderDTO getUserOrderById(Long id, Long orderId);

    UserDTO addUserOrder(Long userId, OrderDTO dto);

    void deleteUserOrder(Long userId, Long orderId);
}
