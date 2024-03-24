package com.example.project.mapper;

import com.example.project.dto.OrderDTO;
import com.example.project.entity.Order;
import com.example.project.entity.User;
import com.example.project.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMapperTest {

    @Autowired
    OrderMapper mapper;

    @Test
    void orderDtoToOrder() {
        LocalDateTime time = LocalDateTime.now();
        User user = new User();
        OrderDTO dto = new OrderDTO(1L, user, time, List.of(), 9.99, OrderStatus.COMPLETED);

        Order order = mapper.orderDtoToOrder(dto);

        assertNotNull(order);
        assertEquals(order.getId(), dto.getId());
        assertEquals(order.getUser(), dto.getUser());
        assertEquals(order.getTotalCost(), dto.getTotalCost());
        assertEquals(order.getStatus(), dto.getStatus());
        assertEquals(order.getOrderTime(), dto.getOrderTime());
    }

    @Test
    void orderToOrderDto() {
        LocalDateTime time = LocalDateTime.now();
        User user = new User();
        Order order = new Order(1L, user, time, List.of(), 9.99, OrderStatus.CANCELLED);

        OrderDTO dto = mapper.orderToOrderDto(order);

        assertNotNull(dto);
        assertEquals(dto.getId(), order.getId());
        assertEquals(dto.getUser(), order.getUser());
        assertEquals(dto.getTotalCost(), order.getTotalCost());
        assertEquals(dto.getStatus(), order.getStatus());
    }
}