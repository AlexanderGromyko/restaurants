package com.github.alexandergromyko.restaurants.model;

import com.github.alexandergromyko.restaurants.util.validation.NoHtml;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"}, name = "restaurant_unique_id_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity implements Serializable {
    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 1000)
    @NoHtml
    private String description;
    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("name ASC")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @Schema(hidden = true)
    private List<Dish> dishes;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @Schema(hidden = true)
    private List<Vote> votes;

    public Restaurant(Restaurant r) {
        this(r.id, r.name, r.description);
    }

    public Restaurant(Integer id, String name, String description) {
        this(id, name, description, true);
    }

    public Restaurant(Integer id, String name, String description, boolean enabled) {
        super(id, name);
        this.description = description;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Restaurant:" + id + '[' + description + ']';
    }
}