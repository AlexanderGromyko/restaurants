package com.github.alexandergromyko.restaurants.util;

import com.github.alexandergromyko.restaurants.model.Restaurant;
import com.github.alexandergromyko.restaurants.to.RestaurantTo;
import com.github.alexandergromyko.restaurants.to.RestaurantWithDishesTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantsUtil {
    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> createTo(restaurant))
                .collect(Collectors.toList());
    }

    public static List<RestaurantWithDishesTo> getWithDishesTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> createWithDishesTo(restaurant))
                .collect(Collectors.toList());
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.isEnabled());
    }

    public static RestaurantWithDishesTo createWithDishesTo(Restaurant restaurant) {
        return new RestaurantWithDishesTo(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.isEnabled(), restaurant.getDishes());
    }
}