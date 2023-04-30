package com.example.restaurants.web.restaurant;

import com.example.restaurants.model.Restaurant;
import com.example.restaurants.to.RestaurantTo;
import com.example.restaurants.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);
    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);
    public static final int RESTAURANT1_ID = 1;
    public static final int ADMIN_MEAL_ID = 8;
    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "IKEA cafe", "Cafe for IKEA customers");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Cafe", "Small cafe");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "British pub", "Famous British pub");
    public static final List<Restaurant> restaurants = List.of(restaurant3, restaurant2, restaurant1);
}