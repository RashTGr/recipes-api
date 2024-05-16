package org.raul.receipesweb.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.raul.receipesweb.utils.RecipeStepKey;

@Entity
@Getter
@Setter
public class RecipeStep {

    @EmbeddedId
    private RecipeStepKey id;

    // private Integer stepNumber;
    private String description;
    private Integer timePerStepMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId") // to map recipeId attribute of the embedded id
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
