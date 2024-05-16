package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
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
public class RecipeStepService {

    private final RecipeStepRepository stepsRepository;
    private final RecipeRepository recipeRepository;

    @Transactional(readOnly = true)
    public RecipeStepDTO findById(RecipeStepKey id) {
        RecipeStep recipeStep = stepsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe step with this ID not found!"));
        return new RecipeStepDTO(
                recipeStep.getId().getStepNumber(),
                recipeStep.getDescription(),
                recipeStep.getTimePerStepMinutes()
        );
    }

    public RecipeStepDTO saveRecipeStep(Long recipeId, RecipeStepDTO recipeStepDTO) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with ID: " + recipeId));

        RecipeStep recipeStep = new RecipeStep();
        RecipeStepKey id = new RecipeStepKey(recipeId, recipeStepDTO.getStepNo());

        recipeStep.setId(id);
        recipeStep.setDescription(recipeStepDTO.getDescription());
        recipeStep.setTimePerStepMinutes(recipeStepDTO.getTimePerStepMinutes());
        recipeStep.setRecipe(recipe);

        recipeStep = stepsRepository.save(recipeStep);

        return new RecipeStepDTO(
                recipeStep.getId().getStepNumber(),
                recipeStep.getDescription(),
                recipeStep.getTimePerStepMinutes()
        );
    }
}
