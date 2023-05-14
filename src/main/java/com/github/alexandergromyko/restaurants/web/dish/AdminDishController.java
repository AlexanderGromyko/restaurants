package com.github.alexandergromyko.restaurants.web.dish;

import com.github.alexandergromyko.restaurants.model.Dish;
import com.github.alexandergromyko.restaurants.service.DishService;
import com.github.alexandergromyko.restaurants.to.DishTo;
import com.github.alexandergromyko.restaurants.util.validation.ValidationUtil;
import com.github.alexandergromyko.restaurants.web.restaurant.AdminRestaurantController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminDishController extends AbstractDishController {
    public static final String REST_URL = AdminRestaurantController.REST_URL + "/{restaurantId}/dishes";
    private final DishService service;

    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id, @PathVariable int restaurantId) {
        return super.get(id, restaurantId);
    }

    @GetMapping
    public List<DishTo> getByDate(@PathVariable int restaurantId, @RequestParam("date") Optional<LocalDate> date) {
        return super.getAll(restaurantId, date.orElse(LocalDate.now()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete {} for restaurant {}", id, restaurantId);
        Dish dish = repository.getExistedAndBelonged(restaurantId, id);
        repository.delete(dish);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int id, @PathVariable int restaurantId) {
        log.info("update {} for restaurant {}", dish, restaurantId);
        ValidationUtil.assureIdConsistent(dish, id);
        repository.getExistedAndBelonged(restaurantId, id);
        service.save(restaurantId, dish);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithPlacement(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create {} for restaurant {}", dish, restaurantId);
        ValidationUtil.checkNew(dish);
        Dish created = service.save(restaurantId, dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(AdminRestaurantController.REST_URL + '/' + restaurantId + "/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}