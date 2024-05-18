package org.raul.receipesweb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.RecipeStepDTO;
import org.raul.receipesweb.service.RecipeStepService;
import org.raul.receipesweb.utils.RecipeStepKey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/steps")
public class RecipeStepController {

    private final RecipeStepService recipeStepService;

    @GetMapping("/{recipeId}/{stepNumber}")
    public ResponseEntity<RecipeStepDTO> getRecipeStep(@PathVariable @Min(1) Long recipeId,
                                                       @PathVariable @Min(1) Integer stepNumber) {
        RecipeStepKey id = new RecipeStepKey(recipeId, stepNumber);
        RecipeStepDTO recipeStepDTO = recipeStepService.findById(id);
        return ResponseEntity.ok(recipeStepDTO);
    }

    @PostMapping("/{recipeId}")
    public ResponseEntity<RecipeStepDTO> addRecipeStep(@PathVariable @Min(1) Long recipeId,
                                                       @Valid @RequestBody RecipeStepDTO recipeStepDTO) {
        RecipeStepDTO saveStep = recipeStepService.saveRecipeStep(recipeId, recipeStepDTO);
        return new ResponseEntity<>(saveStep, HttpStatus.CREATED);
    }

    @PutMapping("/{recipeId}/{stepNumber}")
    public ResponseEntity<RecipeStepDTO> updateRecipeStep(@PathVariable @Min(1) Long recipeId,
                                                          @PathVariable @Min(1) Integer stepNumber,
                                                          @Valid @RequestBody RecipeStepDTO recipeStepDTO) {
        RecipeStepDTO updatedDTO = recipeStepService.updateStep(recipeId, stepNumber, recipeStepDTO);

        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}/{stepNumber}")
    public ResponseEntity<Void> deleteRecipeStep(@PathVariable @Min(1) Long recipeId,
                                                 @PathVariable @Min(1) Integer stepNumber) {
        RecipeStepKey id = new RecipeStepKey(recipeId, stepNumber);
        recipeStepService.deleteRecipeStep(id);

        return ResponseEntity.noContent().build();
    }
}
