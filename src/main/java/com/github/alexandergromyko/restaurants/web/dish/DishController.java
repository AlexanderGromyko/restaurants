package com.github.alexandergromyko.restaurants.web.dish;

import com.github.alexandergromyko.restaurants.model.Dish;
import com.github.alexandergromyko.restaurants.to.DishTo;
import com.github.alexandergromyko.restaurants.web.restaurant.RestaurantController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class DishController extends AbstractDishController {
    public static final String REST_URL = RestaurantController.REST_URL + "/{restaurantId}/dishes";

    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id, @PathVariable int restaurantId) {
        return super.get(id, restaurantId);
    }

    @GetMapping
    public List<DishTo> getByDate(@PathVariable int restaurantId) {
        return super.getAll(restaurantId, LocalDate.now());
    }
}