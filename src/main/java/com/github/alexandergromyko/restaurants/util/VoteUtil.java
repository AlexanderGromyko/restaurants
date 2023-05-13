package com.github.alexandergromyko.restaurants.util;

import com.github.alexandergromyko.restaurants.model.Vote;
import com.github.alexandergromyko.restaurants.to.VoteTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VoteUtil {
    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDate(), vote.getRestaurant().getId());
    }
}