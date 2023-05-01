package com.example.restaurants.web.vote;

import com.example.restaurants.model.Dish;
import com.example.restaurants.model.User;
import com.example.restaurants.model.Vote;
import com.example.restaurants.repository.BaseRepository;
import com.example.restaurants.to.DishTo;
import com.example.restaurants.web.restaurant.RestaurantController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = com.example.restaurants.web.dish.DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {
    public static final String REST_URL = RestaurantController.REST_URL + "/{restaurantId}/votes";

    BaseRepository<Vote> voteRepository;

//    @GetMapping("/{id}")
//    public ResponseEntity<Dish> get(@PathVariable int id, @PathVariable int restaurantId) {
//        return super.get(id, restaurantId);
//    }
//
//    @GetMapping
//    public List<DishTo> getAll(@PathVariable int restaurantId) {
//        return super.getAll(restaurantId);
//    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId) {
        log.info("delete vote for restaurant {}", restaurantId);
        voteRepository.deleteExisted(restaurantId);
    }
}
