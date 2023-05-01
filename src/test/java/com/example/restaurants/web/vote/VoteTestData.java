package com.example.restaurants.web.vote;

import com.example.restaurants.model.Vote;
import com.example.restaurants.web.MatcherFactory;

import static com.example.restaurants.web.restaurant.RestaurantTestData.restaurant2;
import static com.example.restaurants.web.restaurant.RestaurantTestData.restaurant3;
import static com.example.restaurants.web.user.UserTestData.user;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");
    public static final Vote VOTE_OF_USER = new Vote(3, user, restaurant2);

    public static Vote getNew() {
        return new Vote(null, user, restaurant3);
    }
}