package org.raul.receipesweb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.raul.receipesweb.utils.RecipeStepKey;

@AllArgsConstructor
@Getter
@Setter
public class RecipeStepDTO {
    @Min(value = 1, message = "Step number must be greater than 0")
    private Integer stepNo;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @NotBlank(message = "Description must not be empty")
    private String description;

    @Min(value = 1, message = "Time per step must be at least 1 minute")
    @NotNull(message = "Time per step must not be null")
    private Integer timePerStepMinutes;
}
