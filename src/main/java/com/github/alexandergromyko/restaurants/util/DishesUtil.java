package com.github.alexandergromyko.restaurants.util;

import com.github.alexandergromyko.restaurants.model.Dish;
import com.github.alexandergromyko.restaurants.to.DishTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DishesUtil {
    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream()
                .map(dish -> createTo(dish))
                .collect(Collectors.toList());
    }

    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getDate(), dish.getDescription(), dish.getPrice());
    }
}
