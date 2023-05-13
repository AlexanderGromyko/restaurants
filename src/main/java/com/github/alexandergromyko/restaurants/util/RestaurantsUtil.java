package com.github.alexandergromyko.restaurants.util;

import com.github.alexandergromyko.restaurants.model.Restaurant;
import com.github.alexandergromyko.restaurants.to.RestaurantTo;
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

    public static RestaurantTo createTo(Restaurant restaurant) {
        int votes = 0;
        if(restaurant.getVotes() != null){
            votes = restaurant.getVotes().size();
        }
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.isEnabled(), votes);
    }
}