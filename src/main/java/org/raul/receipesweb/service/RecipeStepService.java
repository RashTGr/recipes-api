package org.raul.receipesweb.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raul.receipesweb.dto.RecipeStepDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Recipe;
import org.raul.receipesweb.model.RecipeStep;
import org.raul.receipesweb.repository.RecipeRepository;
import org.raul.receipesweb.repository.RecipeStepRepository;
import org.raul.receipesweb.utils.RecipeStepKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeStepService {

    private final RecipeStepRepository stepsRepository;
    private final RecipeRepository recipeRepository;

    public RecipeStepDTO findById(RecipeStepKey id) {
        log.info("Fetching recipe step by ID: {}", id);

        RecipeStep recipeStep = stepsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe step with this ID not found!"));

        return new RecipeStepDTO(
                recipeStep.getId().getStepNumber(),
                recipeStep.getDescription(),
                recipeStep.getTimePerStepMinutes()
        );
    }

    public RecipeStepDTO saveRecipeStep(Long recipeId, RecipeStepDTO recipeStepDTO) {
        log.info("Saving new recipe step for recipe ID: {}", recipeId);

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with ID: " + recipeId));

        RecipeStep recipeStep = new RecipeStep();
        RecipeStepKey id = new RecipeStepKey(recipeId, recipeStepDTO.getStepNo());

        recipeStep.setId(id);
        recipeStep.setDescription(recipeStepDTO.getDescription());
        recipeStep.setTimePerStepMinutes(recipeStepDTO.getTimePerStepMinutes());
        recipeStep.setRecipe(recipe);

        recipeStep = stepsRepository.save(recipeStep);
        log.info("Saved new recipe step for recipe ID: {}, Step No: {}", recipeId, recipeStepDTO.getStepNo());

        return new RecipeStepDTO(
                recipeStep.getId().getStepNumber(),
                recipeStep.getDescription(),
                recipeStep.getTimePerStepMinutes()
        );
    }

    public RecipeStepDTO updateStep(Long recipeId, Integer stepNo, RecipeStepDTO recipeStepDTO) {
        log.info("Updating recipe step for recipe ID: {}, Step No: {}", recipeId, stepNo);

        RecipeStepKey id = new RecipeStepKey(recipeId, stepNo); // creating composite key

        // Find if this recipe exists with the created composite key
        RecipeStep existingRecipeStep = stepsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe step with this ID not found!"));

        existingRecipeStep.setDescription(recipeStepDTO.getDescription());
        existingRecipeStep.setTimePerStepMinutes(recipeStepDTO.getTimePerStepMinutes());

        RecipeStep updated = stepsRepository.save(existingRecipeStep);
        log.info("Updated recipe step for recipe ID: {}, Step No: {}", recipeId, stepNo);

        return new RecipeStepDTO(
                updated.getId().getStepNumber(),
                updated.getDescription(),
                updated.getTimePerStepMinutes()
        );
    }

    public void deleteRecipeStep(RecipeStepKey id) {
        log.info("Deleting recipe step with ID: {}", id);

        if (!stepsRepository.existsById(id)) {
            throw new EntityNotFoundException("No step found to delete for ID: " + id);
        }
        stepsRepository.deleteById(id);
        log.info("Deleted recipe step with ID: {}", id);
    }
}
