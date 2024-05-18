package org.raul.receipesweb.controller;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.RecipeWithCategoriesDTO;
import org.raul.receipesweb.service.RecipeCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recipes/categories")
@RequiredArgsConstructor
public class RecipeCategoryController {

    private final RecipeCategoryService recipeCategoryService;

    @GetMapping
    public ResponseEntity<List<RecipeWithCategoriesDTO>> getAllRecipesWithCategories() {
        List<RecipeWithCategoriesDTO> recipes = recipeCategoryService.getAllRecipesWithCategories();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeWithCategoriesDTO> getRecipeWithCategories(@PathVariable Long id) {
        RecipeWithCategoriesDTO recipe = recipeCategoryService.getRecipeWithCategories(id);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<RecipeWithCategoriesDTO> updateRecipeCategories(@PathVariable Long recipeId, @RequestBody Set<Long> categoryIds) {
        RecipeWithCategoriesDTO updatedRecipe = recipeCategoryService.updateRecipeCategories(recipeId, categoryIds);
        return ResponseEntity.ok(updatedRecipe);
    }

    @PostMapping("/{recipeId}/{categoryId}")
    public ResponseEntity<RecipeWithCategoriesDTO> addCategoryToRecipe(@PathVariable Long recipeId, @PathVariable Long categoryId) {
        RecipeWithCategoriesDTO updatedRecipe = recipeCategoryService.addCategoryToRecipe(recipeId, categoryId);
        return ResponseEntity.ok(updatedRecipe);
    }
}

