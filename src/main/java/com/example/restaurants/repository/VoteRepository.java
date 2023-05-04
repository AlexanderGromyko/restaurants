package com.example.restaurants.repository;

import com.example.restaurants.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Transactional
public interface VoteRepository extends BaseRepository<Vote> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    int delete(int userId, LocalDate date);

    @EntityGraph(attributePaths =  {"restaurant"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    Optional<Vote> get(int userId, LocalDate date);
}