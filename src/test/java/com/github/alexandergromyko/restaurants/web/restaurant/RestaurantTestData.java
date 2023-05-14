package com.github.alexandergromyko.restaurants.web.restaurant;

import com.github.alexandergromyko.restaurants.model.Restaurant;
import com.github.alexandergromyko.restaurants.to.RestaurantTo;
import com.github.alexandergromyko.restaurants.to.RestaurantWithDishesTo;
import com.github.alexandergromyko.restaurants.util.RestaurantsUtil;
import com.github.alexandergromyko.restaurants.web.MatcherFactory;

import java.util.List;

import static com.github.alexandergromyko.restaurants.web.dish.DishTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);
    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);
    public static MatcherFactory.Matcher<RestaurantWithDishesTo> RESTAURANT_WITH_DISHES_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantWithDishesTo.class);
    public static final String REST_URL_USER = RestaurantController.REST_URL;
    public static final String REST_URL_ADMIN = AdminRestaurantController.REST_URL;
    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final int RESTAURANT3_ID = 3;
    public static final int RESTAURANT999_ID = 999;
    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "IKEA cafe", "Cafe for IKEA customers");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Cafe", "Small cafe");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "British pub", "Famous British pub");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT1_ID + 3, "Closed pub", "Famous British pub, but closed", false);
    public static final RestaurantTo restaurantTo2 = RestaurantsUtil.createTo(restaurant2);
    public static final List<Restaurant> allRestaurants = List.of(restaurant3, restaurant2, restaurant4, restaurant1);
    public static final List<Restaurant> enabledRestaurants = List.of(restaurant3, restaurant2, restaurant1);

    public static Restaurant getNew() {
        return new Restaurant(null, "new restaurant name", "new restaurant description");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT2_ID, "updated restaurant name", "updated restaurant description");
    }

    public static List<Restaurant> getEnabledRestaurantWithDishes() {
        restaurant1.setDishes(List.of(dish2, dish1, dish3, dish4));
        restaurant2.setDishes(List.of(dish5, dish8, dish7, dish6));
        restaurant3.setDishes(List.of(dish11, dish10, dish9));
        return List.of(restaurant3, restaurant2, restaurant1);
    }
}