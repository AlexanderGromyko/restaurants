package com.github.alexandergromyko.restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {
    LocalDate date;
    String description;
    int price;

    public DishTo(Integer id, String name, LocalDate date, String description, Integer price) {
        super(id, name);
        this.date = date;
        this.description = description;
        this.price = price;
    }
}
