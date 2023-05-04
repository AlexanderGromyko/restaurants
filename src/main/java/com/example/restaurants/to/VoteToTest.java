package com.example.restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteToTest extends BaseTo{
    //Difference from VoteTo is only no @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDate date;

    @Range(min=1)
    int restaurantId;

    public VoteToTest(Integer id, LocalDate date, int restaurantId) {
        super(id);
        this.date = date;
        this.restaurantId = restaurantId;
    }
}
