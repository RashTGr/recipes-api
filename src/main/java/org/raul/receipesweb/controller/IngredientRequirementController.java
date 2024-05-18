package org.raul.receipesweb.controller;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.IngredientRequirementDTO;
import org.raul.receipesweb.service.IngredientRequirementService;
import org.raul.receipesweb.utils.RecipeIngredientKey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients/req")
@RequiredArgsConstructor
public class IngredientRequirementController {

    private final IngredientRequirementService ingredientRequirementService;

    @GetMapping("/{recipeId}/{ingredientId}")
    public ResponseEntity<IngredientRequirementDTO> getIngredientRequirement(@PathVariable Long recipeId,
                                                                             @PathVariable Long ingredientId) {
        RecipeIngredientKey id = new RecipeIngredientKey(recipeId, ingredientId);
        IngredientRequirementDTO dto = ingredientRequirementService.getIngredientReqById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<IngredientRequirementDTO> addIngredientRequirement(@RequestBody IngredientRequirementDTO dto) {
        IngredientRequirementDTO createdDto = ingredientRequirementService.addIngredientRequirement(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/{recipeId}/{ingredientId}")
    public ResponseEntity<IngredientRequirementDTO> updateIngredientRequirement(
            @PathVariable Long recipeId,
            @PathVariable Long ingredientId,
            @RequestBody IngredientRequirementDTO dto) {
        dto.setRecipeId(recipeId);
        dto.setIngredientId(ingredientId);
        IngredientRequirementDTO updatedDto = ingredientRequirementService.updateIngredientRequirement(dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{recipeId}/{ingredientId}")
    public ResponseEntity<Void> deleteIngredientRequirement(@PathVariable Long recipeId,
                                                            @PathVariable Long ingredientId) {
        ingredientRequirementService.deleteIngredientRequirement(recipeId, ingredientId);

        return ResponseEntity.noContent().build();
    }
}