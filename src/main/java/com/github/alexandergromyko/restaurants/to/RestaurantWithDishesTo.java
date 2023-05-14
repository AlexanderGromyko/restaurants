package com.github.alexandergromyko.restaurants.to;

import com.github.alexandergromyko.restaurants.model.Dish;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantWithDishesTo extends NamedTo {
    String description;
    boolean enabled;
    public List<Dish> dishes;

    public RestaurantWithDishesTo(Integer id, String name, String description, boolean enabled, List<Dish> dishes) {
        super(id, name);
        this.description = description;
        this.enabled = enabled;
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "RestaurantWithDishesTo:" + id + '[' + name + ']';
    }
}
