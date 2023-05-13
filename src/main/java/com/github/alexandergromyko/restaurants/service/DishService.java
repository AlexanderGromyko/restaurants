package com.github.alexandergromyko.restaurants.service;

import com.github.alexandergromyko.restaurants.model.Dish;
import com.github.alexandergromyko.restaurants.repository.DishRepository;
import com.github.alexandergromyko.restaurants.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Dish save(int restaurantId, Dish dish) {
        dish.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return dishRepository.save(dish);
    }
}
