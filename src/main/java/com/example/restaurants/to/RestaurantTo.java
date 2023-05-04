package com.example.restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo{
    String description;

    boolean enabled;

    int votes;

    public RestaurantTo(Integer id, String name, String description, boolean enabled, int votes) {
        super(id, name);
        this.description = description;
        this.enabled = enabled;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo:" + id + '[' + name + ']';
    }
}
