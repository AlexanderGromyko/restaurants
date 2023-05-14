package com.github.alexandergromyko.restaurants.repository;

import com.github.alexandergromyko.restaurants.error.DataConflictException;
import com.github.alexandergromyko.restaurants.error.NotFoundException;
import com.github.alexandergromyko.restaurants.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    int delete(int userId, LocalDate date);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    Optional<Vote> get(int userId, LocalDate date);

    default boolean isNotExistedOnDate(int userId, LocalDate date) {
        if (get(userId, date).isEmpty()) {
            return true;
        } else throw new DataConflictException("vote with userId=" + userId + " on date= " + date + " already exists");
    }

    default Vote getExistedOnDate(int userId, LocalDate date) {
        return get(userId, date).orElseThrow(() -> new NotFoundException("vote with userId=" + userId + " on date= " + date + " doesn't exist"));
    }
}