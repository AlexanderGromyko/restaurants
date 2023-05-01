package com.example.restaurants.model;

import com.example.restaurants.util.validation.NoHtml;
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

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @NoHtml
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("name ASC")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @Schema(hidden = true)
    private List<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    //@OrderBy("user ASC")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @Schema(hidden = true)
    private List<Vote> votes;

    public Restaurant(Restaurant r) {
        this(r.id, r.name, r.description);
    }

    public Restaurant(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    @Override
    public String toString() {
        return "Restaurant:" + id + '[' + description + ']';
    }
}