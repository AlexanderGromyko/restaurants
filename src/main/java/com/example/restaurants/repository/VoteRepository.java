package com.example.restaurants.repository;

import com.example.restaurants.model.Vote;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional()
public interface VoteRepository extends BaseRepository<Vote> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.restaurant.id=:restaurantId")
    int delete(int userId, int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId and v.restaurant.id=:restaurantId")
    Optional<Vote> get(int userId, int restaurantId);
}