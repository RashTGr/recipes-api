package org.raul.receipesweb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.IngredientRequirementDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Ingredient;
import org.raul.receipesweb.model.IngredientRequirement;
import org.raul.receipesweb.model.Recipe;
import org.raul.receipesweb.repository.IngredientRepository;
import org.raul.receipesweb.repository.IngredientRequirementRepository;
import org.raul.receipesweb.repository.RecipeRepository;
import org.raul.receipesweb.utils.RecipeIngredientKey;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientRequirementService {

    private final IngredientRequirementRepository ingredientRequirementRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientRequirementDTO getIngredientReqById(RecipeIngredientKey id) {
        IngredientRequirement ingredientReq = ingredientRequirementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find recipe with id :: " + id.getRecipeId()
                + " and ingredient ID: " + id.getIngredientId()));

        return new IngredientRequirementDTO(
                ingredientReq.getRecipe().getId(),
                ingredientReq.getIngredient().getId(),
                ingredientReq.getQuantity(),
                ingredientReq.getUnit());
    }

    @Transactional
    public IngredientRequirementDTO addIngredientRequirement(IngredientRequirementDTO dto) {
        Long recipeId = dto.getRecipeId();
        Long ingredientId = dto.getIngredientId();

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + recipeId));
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with ID: " + ingredientId));

        IngredientRequirement requirement = new IngredientRequirement();
        requirement.setId(new RecipeIngredientKey(recipeId, ingredientId));
        requirement.setRecipe(recipe);
        requirement.setIngredient(ingredient);
        requirement.setQuantity(dto.getQuantity());
        requirement.setUnit(dto.getUnit());

        ingredientRequirementRepository.save(requirement);

        return new IngredientRequirementDTO(
                requirement.getRecipe().getId(),
                requirement.getIngredient().getId(),
                requirement.getQuantity(),
                requirement.getUnit());
    }
}
