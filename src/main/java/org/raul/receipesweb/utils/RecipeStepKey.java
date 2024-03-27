package org.raul.receipesweb.utils;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/* primary key of recipe_step table is combination
of recipeId and stepNumber. Therefore, this class
is created to handle this structure*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class RecipeStepKey implements Serializable {
    private Long recipeId;
    private Integer stepNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeStepKey that = (RecipeStepKey) o;

        if (!Objects.equals(recipeId, that.recipeId)) return false;
        return Objects.equals(stepNumber, that.stepNumber);
    }

    @Override
    public int hashCode() {
        int result = recipeId != null ? recipeId.hashCode() : 0;
        result = 31 * result + (stepNumber != null ? stepNumber.hashCode() : 0);
        return result;
    }
}
