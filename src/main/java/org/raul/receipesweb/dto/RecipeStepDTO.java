package org.raul.receipesweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecipeStepDTO {
    private Integer stepNumber;
    private String description;
    private Integer timePerStepMinutes;
}
