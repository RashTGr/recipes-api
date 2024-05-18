package org.raul.receipesweb.service;

import lombok.extern.slf4j.Slf4j;
import org.raul.receipesweb.dto.RecipeDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Recipe;
import org.raul.receipesweb.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> getAllRecipes() {
        log.info("Fetching all recipes from the database.");

        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipe -> new RecipeDTO(
                recipe.getId(),
                recipe.getName(),
                recipe.getImage(),
                recipe.getAverageScore(),
                recipe.getTotalTimeMinutes(),
                recipe.getIsFavourite()
        )).collect(Collectors.toList());
    }

    public RecipeDTO getRecipeById(Long id) {
        log.info("Fetching recipe by ID: {}", id);

        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        return new RecipeDTO(
                recipe.getId(),
                recipe.getName(),
                recipe.getImage(),
                recipe.getAverageScore(),
                recipe.getTotalTimeMinutes(),
                recipe.getIsFavourite());
    }

    public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
        log.info("Adding new recipe: {}", recipeDTO.getName());

        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe.setImage(recipeDTO.getImage());
        recipe.setAverageScore(recipeDTO.getAverageScore());
        recipe.setTotalTimeMinutes(recipeDTO.getTotalTimeMinutes());
        recipe.setIsFavourite(recipeDTO.getIsFavourite());
        recipe = recipeRepository.save(recipe); // to database
        log.info("Recipe added with ID: {}", recipe.getId());

        return new RecipeDTO(
                recipe.getId(),
                recipe.getName(),
                recipe.getImage(),
                recipe.getAverageScore(),
                recipe.getTotalTimeMinutes(),
                recipe.getIsFavourite());
    }

    public void deleteRecipe(Long id) {
        log.info("Deleting recipe with ID: {}", id);

        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        recipeRepository.delete(recipe);
        log.info("Deleted recipe with ID: {}", id);
    }
}
