package org.raul.receipesweb.controller;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.RecipeStepDTO;
import org.raul.receipesweb.service.RecipeStepService;
import org.raul.receipesweb.utils.RecipeStepKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/steps")
public class RecipeStepController {

    private final RecipeStepService recipeStepService;

    @GetMapping("/{recipeId}/{stepNumber}")
    public ResponseEntity<RecipeStepDTO> getRecipeStep(@PathVariable Long recipeId, @PathVariable Integer stepNumber) {
        RecipeStepKey id = new RecipeStepKey(recipeId, stepNumber);
        RecipeStepDTO recipeStepDTO = recipeStepService.findById(id);
        return ResponseEntity.ok(recipeStepDTO);
    }

    @PostMapping("/{recipeId}")
    public ResponseEntity<RecipeStepDTO> addRecipeStep(@PathVariable Long recipeId, @RequestBody RecipeStepDTO recipeStepDTO) {
        RecipeStepDTO saveStep = recipeStepService.saveRecipeStep(recipeId, recipeStepDTO);
        return new ResponseEntity<>(saveStep, HttpStatus.CREATED);
    }
}
