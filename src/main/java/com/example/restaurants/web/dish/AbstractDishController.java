package com.example.restaurants.web.dish;

import com.example.restaurants.model.Dish;
import com.example.restaurants.repository.DishRepository;
import com.example.restaurants.to.DishTo;
import com.example.restaurants.util.DishesUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractDishController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected DishRepository repository;

    public ResponseEntity<Dish> get(int id, int restaurantId) {
        log.info("get dish {} for restaurant {}", id, restaurantId);
        return ResponseEntity.of(repository.get(restaurantId, id));
    }

    public List<DishTo> getAll(int restaurantId, LocalDate date) {
        log.info("getAll for restaurant {}", restaurantId);
        return DishesUtil.getTos(repository.getAll(restaurantId, date));
    }
}
