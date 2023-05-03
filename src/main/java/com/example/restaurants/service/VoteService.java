package com.example.restaurants.service;

import com.example.restaurants.error.IllegalRequestDataException;
import com.example.restaurants.model.Restaurant;
import com.example.restaurants.model.User;
import com.example.restaurants.model.Vote;
import com.example.restaurants.repository.RestaurantRepository;
import com.example.restaurants.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    private final RestaurantRepository restaurantRepository;

    public static final LocalTime GOOD_TIME_TO_VOTE = LocalTime.of(11, 0, 0);

    @Transactional
    public Vote save(User user, int restaurantId, LocalDate voteDate) {
        if(itIsGoodTimeToMakeVote()) {
            Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
            Vote newVote = new Vote(null, voteDate, user, restaurant);
            return voteRepository.save(newVote);
        } else throw new IllegalRequestDataException("It's not allowed to change vote after 11:00:00");
    }

    public int delete(int userId, int restaurantId, LocalDate voteDate) {
        if(itIsGoodTimeToMakeVote()) {
            return voteRepository.delete(userId, restaurantId, voteDate);
        } else throw new IllegalRequestDataException("It's not allowed to change vote after 11:00:00");
    }

    public static boolean itIsGoodTimeToMakeVote() {
        return LocalTime.now().isBefore(GOOD_TIME_TO_VOTE);
    }
}