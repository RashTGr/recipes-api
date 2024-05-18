package org.raul.receipesweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeWithCategoriesDTO {
    private Long id;
    private String name;
    private String image;
    private Set<CategoryDTO> categories;
    private int numberOfSteps;
    private int numberOfIngredients;
}
