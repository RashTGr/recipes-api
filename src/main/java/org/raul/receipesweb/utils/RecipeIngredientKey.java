package org.raul.receipesweb.utils;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class RecipeIngredientKey implements Serializable {
    private Long recipeId;
    private Long ingredientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeIngredientKey that = (RecipeIngredientKey) o;

        if (!Objects.equals(recipeId, that.recipeId)) return false;
        return Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        int result = recipeId != null ? recipeId.hashCode() : 0;
        result = 31 * result + (ingredientId != null ? ingredientId.hashCode() : 0);
        return result;
    }
}
