package org.raul.receipesweb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UnitDTO {
    private Long id;

    @NotEmpty(message = "unit name cannot be null or empty")
    private String name;
}
