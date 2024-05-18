package org.raul.receipesweb.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequirementDTO {
    private Long recipeId;
    private Long ingredientId;

    @NotNull(message = "Quantity value cannot be null")
    @DecimalMin(value = "0.01", message = "Quantity must be greater than 0")
    private Double quantity;

    private Long unitId;

    private String unitName;
}
