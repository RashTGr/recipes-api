package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raul.receipesweb.dto.IngredientDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Ingredient;
import org.raul.receipesweb.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public List<IngredientDTO> getAllIngredients() {
        log.info("Fetching all ingredients from the database.");

        return ingredientRepository.findAll().stream()
                .map(ingredient -> new IngredientDTO(ingredient.getId(), ingredient.getName()))
                .collect(Collectors.toList());
    }

    public IngredientDTO getIngredientById(Long id) {
        log.info("Fetching ingredient by ID: {}", id);

        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName());
    }

    public IngredientDTO addIngredient(IngredientDTO ingredientDTO) {
        log.info("Adding new ingredient with name: {}", ingredientDTO.getName());

        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient = ingredientRepository.save(ingredient);
        log.info("Added ingredient with ID: {}", ingredient.getId());

        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName());
    }

    public void deleteIngredient(Long id) {
        log.info("Deleting ingredient with ID: {}", id);

        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        ingredientRepository.deleteById(id);
        log.info("Deleted ingredient with ID: {}", id);
    }
}
