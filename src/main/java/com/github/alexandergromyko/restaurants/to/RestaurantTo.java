package com.github.alexandergromyko.restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {
    String description;
    boolean enabled;

    public RestaurantTo(Integer id, String name, String description, boolean enabled) {
        super(id, name);
        this.description = description;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "RestaurantTo:" + id + '[' + name + ']';
    }
}
