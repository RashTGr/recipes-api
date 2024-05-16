package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.IngredientDTO;
import org.raul.receipesweb.model.Ingredient;
import org.raul.receipesweb.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.getById(id);
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
        ingredientRepository.deleteById(id);
    }
}
