package com.github.alexandergromyko.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.alexandergromyko.restaurants.util.validation.NoHtml;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "dish_unique_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = {"restaurant"})
public class Dish extends NamedEntity {
    @Column(name = "menu_date", nullable = false)
    @NotNull
    private LocalDate date;
    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 1000)
    @NoHtml
    private String description;
    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 0)
    private Integer price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    public Dish(Integer id, String name, LocalDate date, String description, int price) {
        super(id, name);
        this.date = date;
        this.description = description;
        this.price = price;
    }
}
