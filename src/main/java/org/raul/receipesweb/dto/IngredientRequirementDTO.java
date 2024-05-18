package org.raul.receipesweb.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientRequirementDTO {
    private Long recipeId;
    private Long ingredientId;
    private Double quantity;
    private String unit;
}
