package com.github.alexandergromyko.restaurants.repository;

import com.github.alexandergromyko.restaurants.error.NotFoundException;
import com.github.alexandergromyko.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("SELECT r FROM Restaurant r ORDER BY r.name ASC")
    List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r WHERE r.enabled ORDER BY r.name ASC")
    List<Restaurant> getAllEnadled();

    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Optional<Restaurant> get(int id);

    @Query("SELECT r FROM Restaurant r WHERE r.id=:id AND r.enabled")
    Optional<Restaurant> getEnabled(int id);

    default Restaurant getExistedAndEnabled(int id) {
        Restaurant restaurant = getExisted(id);
        if (restaurant.isEnabled()) {
            return restaurant;
        } else throw new NotFoundException("Restaurant with id=" + id + " not found");
    }
}