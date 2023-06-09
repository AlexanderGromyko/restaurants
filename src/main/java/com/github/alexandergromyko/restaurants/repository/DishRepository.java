package com.github.alexandergromyko.restaurants.repository;

import com.github.alexandergromyko.restaurants.error.DataConflictException;
import com.github.alexandergromyko.restaurants.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.date=:date ORDER BY d.name ASC")
    List<Dish> getByDate(int restaurantId, LocalDate date);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Optional<Dish> get(int restaurantId, int id);

    default Dish getExistedAndBelonged(int restaurantId, int id) {
        return get(restaurantId, id).orElseThrow(
                () -> new DataConflictException("dish id=" + id + " is not exist or doesn't belong to Restaurant with id=" + restaurantId));
    }
}