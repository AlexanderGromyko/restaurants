package com.example.restaurants.web.restaurant;

import com.example.restaurants.repository.RestaurantRepository;
import com.example.restaurants.to.RestaurantTo;
import com.example.restaurants.util.RestaurantsUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractRestaurantController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected RestaurantRepository repository;

    public RestaurantTo get(int id) {
        log.info("get {}", id);
        return RestaurantsUtil.createTo(repository.get(id).orElseThrow());
    }

    public List<RestaurantTo> getAll() {
        log.info("getAll restaurants");
        return RestaurantsUtil.getTos(repository.getAll());
    }
}
