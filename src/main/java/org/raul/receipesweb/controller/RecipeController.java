package org.raul.receipesweb.controller;

import org.raul.receipesweb.dto.RecipeDTO;
import org.raul.receipesweb.model.Recipe;
import org.raul.receipesweb.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        List<RecipeDTO> recipesDTOs = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipesDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
        RecipeDTO recipeDTO = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipeDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<RecipeDTO> addRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO saved = recipeService.addRecipe(recipeDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build(); // success, but won't return body in response
    }
}