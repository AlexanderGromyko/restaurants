package com.example.restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {
    LocalDateTime dateTime;

    String description;

    int price;

    public DishTo(Integer id, String name, LocalDateTime dateTime, String description, Integer price) {
        super(id, name);
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
    }
}
