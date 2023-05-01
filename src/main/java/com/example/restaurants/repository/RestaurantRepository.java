package com.example.restaurants.repository;

import com.example.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r ORDER BY r.name ASC")
    List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant get(int id);
}