package com.github.alexandergromyko.restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo{
    String description;

    boolean enabled;

    int votesNumber;

    public RestaurantTo(Integer id, String name, String description, boolean enabled, int votesNumber) {
        super(id, name);
        this.description = description;
        this.enabled = enabled;
        this.votesNumber = votesNumber;
    }

    @Override
    public String toString() {
        return "RestaurantTo:" + id + '[' + name + ']';
    }
}
