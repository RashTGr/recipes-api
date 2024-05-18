package org.raul.receipesweb.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequirementDTO {
    private Long recipeId;
    private Long ingredientId;
    private Double quantity;
    private Long unitId;
    private String unitName;
}
