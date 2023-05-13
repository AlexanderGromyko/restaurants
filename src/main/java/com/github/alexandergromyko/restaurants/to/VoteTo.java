package com.github.alexandergromyko.restaurants.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDate date;
    @Range(min = 1)
    int restaurantId;

    public VoteTo(Integer id, LocalDate date, int restaurantId) {
        super(id);
        this.date = date;
        this.restaurantId = restaurantId;
    }
}
