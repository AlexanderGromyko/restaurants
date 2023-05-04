package com.example.restaurants.util;

import com.example.restaurants.model.Vote;
import com.example.restaurants.to.VoteTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VoteUtil {
    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDate(), vote.getRestaurant().id());
    }
}