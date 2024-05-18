package org.raul.receipesweb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecipeDTO {
    private Long id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    private String image;
    private Double averageScore;
    private Integer totalTimeMinutes;
    private Boolean isFavourite;
}
