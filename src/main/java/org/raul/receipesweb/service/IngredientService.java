package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.IngredientDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Ingredient;
import org.raul.receipesweb.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(ingredient -> new IngredientDTO(ingredient.getId(), ingredient.getName()))
                .collect(Collectors.toList());
    }

    public IngredientDTO getIngredientById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName()
        );
    }

    public IngredientDTO addIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient = ingredientRepository.save(ingredient);

        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName()
        );
    }

    public void deleteIngredient(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        ingredientRepository.deleteById(id);
    }
}
