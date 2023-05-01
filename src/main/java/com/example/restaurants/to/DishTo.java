package com.example.restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {

    String description;

    int price;

    public DishTo(Integer id, String name, String description, Integer price) {
        super(id, name);
        this.description = description;
        this.price = price;
    }
}
