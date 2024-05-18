package org.raul.receipesweb.service;

import org.raul.receipesweb.dto.RecipeDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Recipe;
import org.raul.receipesweb.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> getAllRecipes() {
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
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe.setImage(recipeDTO.getImage());
        recipe.setAverageScore(recipeDTO.getAverageScore());
        recipe.setTotalTimeMinutes(recipeDTO.getTotalTimeMinutes());
        recipe.setIsFavourite(recipeDTO.getIsFavourite());
        recipe = recipeRepository.save(recipe); // to database

        return new RecipeDTO(
                recipe.getId(),
                recipe.getName(),
                recipe.getImage(),
                recipe.getAverageScore(),
                recipe.getTotalTimeMinutes(),
                recipe.getIsFavourite());
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        recipeRepository.delete(recipe);
    }
}
