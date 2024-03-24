package com.example.project.controller;

import com.example.project.dto.OrderDTO;
import com.example.project.enums.OrderStatus;
import com.example.project.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void testGetAllOrders() {
        Pageable pageable = Pageable.unpaged();
        Page<OrderDTO> expectedPage = new PageImpl<>(List.of());
        when(orderService.getAllOrders(pageable)).thenReturn(expectedPage);

        ResponseEntity<Page<OrderDTO>> responseEntity = orderController.getAllOrders(pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedPage, responseEntity.getBody());
    }

    @Test
    void testGetOrderById() {
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.getOrderById(1L)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> responseEntity = orderController.getOrderById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(orderDTO, responseEntity.getBody());
    }

    @Test
    void testCreateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.createOrder(orderDTO)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> responseEntity = orderController.createOrder(orderDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(orderDTO, responseEntity.getBody());
    }

    @Test
    void testDeleteOrder() {
        ResponseEntity<Void> responseEntity = orderController.deleteOrder(1L);

        verify(orderService, times(1)).deleteOrder(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testChangeOrderStatus() {
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.changeOrderStatus(1L, OrderStatus.COMPLETED)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> responseEntity = orderController.changeOrderStatus(1L, OrderStatus.COMPLETED);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(orderDTO, responseEntity.getBody());
    }

    @Test
    void testAddDishToOrder() {
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.addDishToOrder(1L, 2L)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> responseEntity = orderController.addDishToOrder(1L, 2L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(orderDTO, responseEntity.getBody());
    }

    @Test
    void testRemoveDishFromOrder() {
        ResponseEntity<Void> responseEntity = orderController.removeDishFromOrder(1L, 2L);

        verify(orderService, times(1)).removeDishFromOrder(1L, 2L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}