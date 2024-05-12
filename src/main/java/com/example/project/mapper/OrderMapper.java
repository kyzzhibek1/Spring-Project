package com.example.project.mapper;

import com.example.project.dto.OrderDTO;
import com.example.project.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "user.orders", ignore = true)
    @Mapping(target = "user.authorities", ignore = true)
    Order orderDtoToOrder(OrderDTO orderDTO);

    @Mapping(target = "user.orders", ignore = true)
    @Mapping(target = "user.authorities", ignore = true)
    OrderDTO orderToOrderDto(Order order);
}
