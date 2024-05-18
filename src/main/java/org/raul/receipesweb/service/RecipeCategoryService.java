package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.CategoryDTO;
import org.raul.receipesweb.dto.RecipeWithCategoriesDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Category;
import org.raul.receipesweb.model.Recipe;
import org.raul.receipesweb.repository.CategoryRepository;
import org.raul.receipesweb.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeCategoryService {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public List<RecipeWithCategoriesDTO> getAllRecipesWithCategories() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(this::convertToRecipeWithCategoriesDTO).collect(Collectors.toList());
    }

    public RecipeWithCategoriesDTO getRecipeWithCategories(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with ID: " + recipeId));
        return convertToRecipeWithCategoriesDTO(recipe);
    }

    public RecipeWithCategoriesDTO updateRecipeCategories(Long recipeId, Set<Long> categoryIds) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with ID: " + recipeId));
        Set<Category> categories = categoryIds.stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id)))
                .collect(Collectors.toSet());
        recipe.setCategories(categories);
        recipeRepository.save(recipe);
        return convertToRecipeWithCategoriesDTO(recipe);
    }

    // Add another category to a recipe
    public RecipeWithCategoriesDTO addCategoryToRecipe(Long recipeId, Long categoryId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with ID: " + recipeId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
        recipe.getCategories().add(category);
        recipeRepository.save(recipe);
        return convertToRecipeWithCategoriesDTO(recipe);
    }

    private RecipeWithCategoriesDTO convertToRecipeWithCategoriesDTO(Recipe recipe) {
        Set<CategoryDTO> categoryDTOs = recipe.getCategories().stream()
                .map(cat -> new CategoryDTO(cat.getId(), cat.getName()))
                .collect(Collectors.toSet());
        return new RecipeWithCategoriesDTO(
                recipe.getId(),
                recipe.getName(),
                recipe.getImage(),
                categoryDTOs,
                recipe.getSteps().size(),
                recipe.getIngredientRequirements().size());
    }
}

