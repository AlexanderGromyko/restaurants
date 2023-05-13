package com.github.alexandergromyko.restaurants.service;

import com.github.alexandergromyko.restaurants.error.IllegalRequestDataException;
import com.github.alexandergromyko.restaurants.error.NotFoundException;
import com.github.alexandergromyko.restaurants.model.Restaurant;
import com.github.alexandergromyko.restaurants.model.User;
import com.github.alexandergromyko.restaurants.model.Vote;
import com.github.alexandergromyko.restaurants.repository.RestaurantRepository;
import com.github.alexandergromyko.restaurants.repository.VoteRepository;
import com.github.alexandergromyko.restaurants.to.VoteTo;
import com.github.alexandergromyko.restaurants.util.VoteUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        if (itIsGoodTimeToMakeVote()) {
            Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
            if (!restaurant.isEnabled()) {
                throw new NotFoundException("Entity with id=" + restaurantId + " not found");
            }
            Vote newVote = new Vote(null, date, user, restaurant);
            return VoteUtil.createTo(voteRepository.save(newVote));
        } else
            throw new IllegalRequestDataException("It's not allowed to vote after " + GOOD_TIME_TO_VOTE.format(DateTimeFormatter.ofPattern("HH.mm")));
    }

    @Transactional
    public int delete(int userId, LocalDate date) {
        if (itIsGoodTimeToMakeVote()) {
            return voteRepository.delete(userId, date);
        } else
            throw new IllegalRequestDataException("It's not allowed to change vote after " + GOOD_TIME_TO_VOTE.format(DateTimeFormatter.ofPattern("HH.mm")));
    }

    public static boolean itIsGoodTimeToMakeVote() {
        return LocalTime.now().isBefore(GOOD_TIME_TO_VOTE) && LocalTime.now().isAfter(LocalTime.MIN);
    }
}