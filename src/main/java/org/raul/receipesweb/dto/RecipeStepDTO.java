package org.raul.receipesweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.raul.receipesweb.utils.RecipeStepKey;

@AllArgsConstructor
@Getter
@Setter
public class RecipeStepDTO {
    private Integer stepNo;
    private String description;
    private Integer timePerStepMinutes;
}
