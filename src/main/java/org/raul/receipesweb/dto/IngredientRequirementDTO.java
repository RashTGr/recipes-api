package org.raul.receipesweb.dto;

import lombok.*;

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
