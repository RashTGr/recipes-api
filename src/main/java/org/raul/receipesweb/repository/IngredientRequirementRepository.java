package org.raul.receipesweb.repository;

import org.raul.receipesweb.model.IngredientRequirement;
import org.raul.receipesweb.utils.RecipeIngredientKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRequirementRepository extends JpaRepository<IngredientRequirement, RecipeIngredientKey> {
}
