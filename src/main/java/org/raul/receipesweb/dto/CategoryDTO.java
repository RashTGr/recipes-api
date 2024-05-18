package org.raul.receipesweb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
