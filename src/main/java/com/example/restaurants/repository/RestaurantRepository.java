package com.example.restaurants.repository;

import com.example.restaurants.model.Restaurant;
import com.example.restaurants.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r ORDER BY r.name ASC")
    List<Restaurant> getAll();

    @Query("SELECT r, count(v) FROM Restaurant r LEFT JOIN Vote v " +
            "WHERE v.date=:date AND v.restaurant = r AND r.id=:id")
    Optional<Restaurant> get(int id, LocalDate date);

    @Query("SELECT r, count(v) FROM Restaurant r LEFT JOIN Vote v " +
            "WHERE v.date=CURRENT_DATE() AND v.restaurant = r AND r.id=:id")
    Optional<Restaurant> get(int id);

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:id")
    List<Vote> getVotes(int id);
}