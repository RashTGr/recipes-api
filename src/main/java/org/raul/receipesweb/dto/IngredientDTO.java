package org.raul.receipesweb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private Long id;

    @NotEmpty(message = "Ingredient name must not be empty")
    private String name;
}
