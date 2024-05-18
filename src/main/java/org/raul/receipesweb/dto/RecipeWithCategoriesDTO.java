package org.raul.receipesweb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeWithCategoriesDTO {
    private Long id;

    @NotNull(message = "Name must not be null")
    private String name;

    private String image;

    @NotNull(message = "Categories cannot be null")
    private Set<CategoryDTO> categories;

    private int numberOfSteps;
    private int numberOfIngredients;
}
