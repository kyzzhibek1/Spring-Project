package com.example.project.bootstrap;

import com.example.project.entity.Dish;
import com.example.project.entity.Order;
import com.example.project.entity.Restaurant;
import com.example.project.entity.User;
import com.example.project.enums.OrderStatus;
import com.example.project.repository.DishRepository;
import com.example.project.repository.OrderRepository;
import com.example.project.repository.RestaurantRepository;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final UserRepository userRepository;

    private final DishRepository dishRepository;

    private final RestaurantRepository restaurantRepository;

    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 5; i++) {
            User user = new User((long) i,"First Name user " + i, "Last Name user " + i,
                    "Password", "+99655500000" + i,
                    List.of());
            user = userRepository.save(user);

            Dish dish = new Dish((long) i, "Dish name " + i, "Dish description " + i, (double) i, null);
            dishRepository.save(dish);

            Restaurant restaurant = new Restaurant((long) i, "Restaurant " + i, "Address #" + i, List.of());
            restaurantRepository.save(restaurant);

            Order order = new Order((long) i, user, LocalDateTime.now(), List.of(dish), (double) i, OrderStatus.IN_PROGRESS);
            orderRepository.save(order);
        }
    }
}
