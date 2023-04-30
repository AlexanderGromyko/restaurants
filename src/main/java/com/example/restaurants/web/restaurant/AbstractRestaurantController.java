package com.example.restaurants.web.restaurant;

import com.example.restaurants.model.Restaurant;
import com.example.restaurants.repository.RestaurantRepository;
import com.example.restaurants.to.RestaurantTo;
import com.example.restaurants.util.RestaurantsUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractRestaurantController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected RestaurantRepository repository;

    public ResponseEntity<Restaurant> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.get(id));
    }

    public ResponseEntity<Restaurant> get(int id, LocalDate date) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.get(id, date));
    }

    public List<RestaurantTo> getAll() {
        log.info("getAll restaurants");
        return RestaurantsUtil.getTos(repository.getAll());
    }
}
