package com.example.restaurants.web.vote;

import com.example.restaurants.model.Vote;
import com.example.restaurants.to.VoteToTest;
import com.example.restaurants.web.MatcherFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.restaurants.web.restaurant.RestaurantTestData.restaurant2;
import static com.example.restaurants.web.restaurant.RestaurantTestData.restaurant3;
import static com.example.restaurants.web.user.UserTestData.user;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");
    public static final MatcherFactory.Matcher<VoteToTest> VOTE_TO_TEST_MATCHER = MatcherFactory.usingEqualsComparator(VoteToTest.class);
    public static final Vote VOTE_OF_USER = new Vote(2, user, restaurant2);
    public static final VoteToTest VOTE_TO_RESTAURANT2 = new VoteToTest(2, LocalDate.now(), restaurant2.id());
    public static final String TODAY_STRING_PARAMETER = "?date=" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    public static final String YESTERDAY_STRING_PARAMETER = "?date=" + LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);

    public static Vote getNew() {
        return new Vote(null, user, restaurant3);
    }

    public static VoteToTest getNewToTest() {
        return new VoteToTest(null, LocalDate.now(), restaurant3.id());
    }

    public static VoteToTest createToTest(Vote vote) {
        return new VoteToTest(vote.getId(), vote.getDate(), vote.getRestaurant().id());
    }
}