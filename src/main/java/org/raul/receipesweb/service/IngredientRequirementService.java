package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raul.receipesweb.dto.IngredientRequirementDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Ingredient;
import org.raul.receipesweb.model.IngredientRequirement;
import org.raul.receipesweb.model.Recipe;
import org.raul.receipesweb.model.Unit;
import org.raul.receipesweb.repository.IngredientRepository;
import org.raul.receipesweb.repository.IngredientRequirementRepository;
import org.raul.receipesweb.repository.RecipeRepository;
import org.raul.receipesweb.repository.UnitRepository;
import org.raul.receipesweb.utils.RecipeIngredientKey;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientRequirementService {

    private final IngredientRequirementRepository ingredientRequirementRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitRepository unitRepository;

    public IngredientRequirementDTO getIngredientReqById(RecipeIngredientKey id) {
        log.info("Fetching ingredient requirement for recipe ID {} and ingredient ID {}",
                id.getRecipeId(), id.getIngredientId());

        IngredientRequirement ingredientReq = ingredientRequirementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find recipe with id :: " + id.getRecipeId()
                        + " and ingredient ID: " + id.getIngredientId()));

        String unitName = null;
        Long unitId = null;
        if (ingredientReq.getUnit() != null) {
            unitName = ingredientReq.getUnit().getName();
            unitId = ingredientReq.getUnit().getId();
        }

        return new IngredientRequirementDTO(
                ingredientReq.getRecipe().getId(),
                ingredientReq.getIngredient().getId(),
                ingredientReq.getQuantity(),
                unitId,
                unitName);
    }

    public IngredientRequirementDTO addIngredientRequirement(IngredientRequirementDTO dto) {
        log.info("Adding ingredient requirement for recipe ID {} and ingredient ID {}",
                dto.getRecipeId(), dto.getIngredientId());

        Recipe recipe = recipeRepository.findById(dto.getRecipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + dto.getRecipeId()));
        Ingredient ingredient = ingredientRepository.findById(dto.getIngredientId())
                .orElseThrow(() -> new RuntimeException("Ingredient not found with ID: " + dto.getIngredientId()));
        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found with ID: " + dto.getUnitId()));

        IngredientRequirement requirement = new IngredientRequirement();
        requirement.setId(new RecipeIngredientKey(dto.getRecipeId(), dto.getIngredientId()));
        requirement.setRecipe(recipe);
        requirement.setIngredient(ingredient);
        requirement.setQuantity(dto.getQuantity());
        requirement.setUnit(unit);

        ingredientRequirementRepository.save(requirement);
        log.info("Added ingredient requirement with recipe ID {} and ingredient ID {}",
                dto.getRecipeId(), dto.getIngredientId());

        return new IngredientRequirementDTO(
                requirement.getRecipe().getId(),
                requirement.getIngredient().getId(),
                requirement.getQuantity(),
                requirement.getUnit().getId(),
                requirement.getUnit().getName());
    }

    public IngredientRequirementDTO updateIngredientRequirement(IngredientRequirementDTO dto) {
        log.info("Updating ingredient requirement for recipe ID {} and ingredient ID {}",
                dto.getRecipeId(), dto.getIngredientId());

        RecipeIngredientKey id = new RecipeIngredientKey(dto.getRecipeId(), dto.getIngredientId());
        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found with ID: " + dto.getUnitId()));
        IngredientRequirement existing = ingredientRequirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient Requirement not found"));

        existing.setQuantity(dto.getQuantity());
        existing.setUnit(unit);

        ingredientRequirementRepository.save(existing);
        log.info("Updated ingredient requirement with recipe ID {} and ingredient ID {}",
                dto.getRecipeId(), dto.getIngredientId());

        return new IngredientRequirementDTO(
                existing.getRecipe().getId(),
                existing.getIngredient().getId(),
                existing.getQuantity(),
                existing.getUnit().getId(),
                existing.getUnit().getName());
    }

    public void deleteIngredientRequirement(Long recipeId, Long ingredientId) {
        log.info("Deleting ingredient requirement for recipe ID {} and ingredient ID {}", recipeId, ingredientId);

        RecipeIngredientKey id = new RecipeIngredientKey(recipeId, ingredientId);
        ingredientRequirementRepository.deleteById(id);
        log.info("Deleted ingredient requirement with recipe ID {} and ingredient ID {}", recipeId, ingredientId);
    }
}
