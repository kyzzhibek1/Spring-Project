package com.example.dto;

import com.example.entity.Dish;
import com.example.entity.User;
import com.example.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    @NotNull
    private User user;

    private LocalDateTime orderTime;

    @NotNull
    private List<Dish> dishes;

    @NotNull
    private double totalCost;

    @NotNull
    @NotBlank
    private OrderStatus status;
}
