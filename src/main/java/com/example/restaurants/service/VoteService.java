package com.example.restaurants.service;

import com.example.restaurants.error.IllegalRequestDataException;
import com.example.restaurants.error.NotFoundException;
import com.example.restaurants.model.Restaurant;
import com.example.restaurants.model.User;
import com.example.restaurants.model.Vote;
import com.example.restaurants.repository.RestaurantRepository;
import com.example.restaurants.repository.VoteRepository;
import com.example.restaurants.to.VoteTo;
import com.example.restaurants.util.VoteUtil;
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

    public VoteTo get(int userId, LocalDate voteDate) {
        return VoteUtil.createTo(voteRepository.get(userId, voteDate).get());
    }

    @Transactional
    public VoteTo save(User user, int restaurantId, LocalDate date) {
        if(itIsGoodTimeToMakeVote()) {
            Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
            if(!restaurant.isEnabled()) {
                throw new NotFoundException("Entity with id=" + restaurantId + " not found");
            }
            Vote newVote = new Vote(null, date, user, restaurant);
            return VoteUtil.createTo(voteRepository.save(newVote));
        } else throw new IllegalRequestDataException("It's not allowed to vote after 11:00:00");
    }

    @Transactional
    public int delete(int userId, LocalDate date) {
        if(itIsGoodTimeToMakeVote()) {
            return voteRepository.delete(userId, date);
        } else throw new IllegalRequestDataException("It's not allowed to change vote after 11:00:00");
    }

    public static boolean itIsGoodTimeToMakeVote() {
        return LocalTime.now().isBefore(GOOD_TIME_TO_VOTE);
    }
}