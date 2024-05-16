package org.raul.receipesweb.controller;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.IngredientDTO;
import org.raul.receipesweb.model.Ingredient;
import org.raul.receipesweb.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        List<IngredientDTO> ingredients = ingredientService.getAllIngredients()
                .stream()
                .map(ingredient -> new IngredientDTO(ingredient.getId(), ingredient.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ingredients);
    }

    @PostMapping
    public ResponseEntity<IngredientDTO> addIngredient(@RequestBody IngredientDTO ingredientDTO) {
        IngredientDTO saved = ingredientService.addIngredient(ingredientDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
