package com.example.restaurants.repository;

import com.example.restaurants.model.Vote;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Transactional
public interface VoteRepository extends BaseRepository<Vote> {

    @Modifying
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.restaurant.id=:restaurantId AND v.date=:voteDate")
    int delete(int userId, int restaurantId, LocalDate voteDate);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId and v.restaurant.id=:restaurantId AND v.date=:voteDate")
    Optional<Vote> get(int userId, int restaurantId, LocalDate voteDate);
}