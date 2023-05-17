package com.github.alexandergromyko.restaurants.web.restaurant;

import com.github.alexandergromyko.restaurants.repository.RestaurantRepository;
import com.github.alexandergromyko.restaurants.to.RestaurantTo;
import com.github.alexandergromyko.restaurants.to.RestaurantWithDishesTo;
import com.github.alexandergromyko.restaurants.util.RestaurantsUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestaurantController {
    @Autowired
    protected RestaurantRepository repository;
    public static final String REST_URL = "/api/restaurants";

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("get {}", id);
        return RestaurantsUtil.createTo(repository.getEnabled(id).orElseThrow());
    }

    @Cacheable("restaurants")
    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll restaurants");
        return RestaurantsUtil.getTos(repository.getAllEnadled());
    }

    @GetMapping("/with-today-dishes")
    public List<RestaurantWithDishesTo> getAllWithDishes(@RequestParam Optional<Integer> restaurantId) {
        log.info("getAll with dishes");
        if (restaurantId.isPresent()) {
            return RestaurantsUtil.getWithDishesTos(repository.getAllEnabledByIdWithDishes(restaurantId.get(), LocalDate.now()));
        }
        return RestaurantsUtil.getWithDishesTos(repository.getAllEnabledWithDishes(LocalDate.now()));
    }
}