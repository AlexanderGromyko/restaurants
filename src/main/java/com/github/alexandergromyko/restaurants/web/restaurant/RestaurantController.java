package com.github.alexandergromyko.restaurants.web.restaurant;

import com.github.alexandergromyko.restaurants.repository.RestaurantRepository;
import com.github.alexandergromyko.restaurants.to.RestaurantTo;
import com.github.alexandergromyko.restaurants.util.RestaurantsUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll restaurants");
        return RestaurantsUtil.getTos(repository.getAllEnadled());
    }
}