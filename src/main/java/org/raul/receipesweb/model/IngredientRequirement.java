package org.raul.receipesweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.raul.receipesweb.utils.RecipeIngredientKey;

@Entity
@Getter
@Setter
@Table(name = "recipe_ingredient")
public class IngredientRequirement {

    @EmbeddedId
    private RecipeIngredientKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")  // Maps the 'recipeId' part of the embedded id
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")  // Maps the 'ingredientId' part of the embedded id
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
