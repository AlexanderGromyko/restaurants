package com.github.alexandergromyko.restaurants.service;

import com.github.alexandergromyko.restaurants.error.IllegalRequestDataException;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    public static final LocalTime GOOD_TIME_TO_VOTE = LocalTime.of(11, 0, 0);

    public VoteTo get(int userId, LocalDate voteDate) {
        return VoteUtil.createTo(voteRepository.getExistedOnDate(userId, voteDate));
    }

    @Transactional
    public VoteTo update(User user, int restaurantId) {
        LocalDateTime voteMoment = getVoteDateTime();
        if (itIsGoodTimeToMakeVote(voteMoment)) {
            Vote existedVote = voteRepository.getExistedOnDate(user.getId(), voteMoment.toLocalDate());
            existedVote.setRestaurant(restaurantRepository.getExistedAndEnabled(restaurantId));
            return VoteUtil.createTo(voteRepository.save(existedVote));
        } else
            throw new IllegalRequestDataException("It's not allowed to update a vote after " + GOOD_TIME_TO_VOTE.format(DateTimeFormatter.ofPattern("HH.mm")));
    }

    @Transactional
    public VoteTo save(User user, int restaurantId) {
        LocalDateTime voteMoment = getVoteDateTime();
        voteRepository.isNotExistedOnDate(user.getId(), voteMoment.toLocalDate());
        Vote newVote = new Vote(null, voteMoment.toLocalDate(), user, restaurantRepository.getExistedAndEnabled(restaurantId));
        return VoteUtil.createTo(voteRepository.save(newVote));
    }

    @Transactional
    public int delete(int userId) {
        LocalDateTime voteMoment = getVoteDateTime();
        if (itIsGoodTimeToMakeVote(voteMoment)) {
            return voteRepository.delete(userId, voteMoment.toLocalDate());
        } else
            throw new IllegalRequestDataException("It's not allowed to delete a vote after " + GOOD_TIME_TO_VOTE.format(DateTimeFormatter.ofPattern("HH.mm")));
    }

    public static boolean itIsGoodTimeToMakeVote(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime().isBefore(GOOD_TIME_TO_VOTE) && localDateTime.toLocalTime().isAfter(LocalTime.MIN);
    }

    public static LocalDateTime getVoteDateTime() {
        return LocalDateTime.now();
    }
}