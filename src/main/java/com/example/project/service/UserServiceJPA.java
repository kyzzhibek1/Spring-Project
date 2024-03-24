package com.example.project.service;

import com.example.project.controller.NotFoundException;
import com.example.project.dto.OrderDTO;
import com.example.project.dto.UserDTO;
import com.example.project.entity.Order;
import com.example.project.entity.User;
import com.example.project.mapper.OrderMapper;
import com.example.project.mapper.UserMapper;
import com.example.project.repository.OrderRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceJPA implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::userToUserDto);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setOrders(List.of());
        user = userRepository.save(user);
        userDto = userMapper.userToUserDto(user);
        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public Page<OrderDTO> getUserOrders(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<Order> orders = user.getOrders();
        List<OrderDTO> orderDtos = orders.stream()
                .map(orderMapper::orderToOrderDto)
                .toList();
        return new PageImpl<>(orderDtos, pageable, orderDtos.size());
    }

    @Override
    public OrderDTO getUserOrderById(Long userId, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Order> optionalOrder = user.getOrders().stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst();
        Order order = optionalOrder.orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public UserDTO addUserOrder(Long userId, OrderDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getOrders().add(orderMapper.orderDtoToOrder(dto));
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public void deleteUserOrder(Long userId, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Order> optionalOrder = user.getOrders().stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst();
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            user.getOrders().remove(order);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
}
