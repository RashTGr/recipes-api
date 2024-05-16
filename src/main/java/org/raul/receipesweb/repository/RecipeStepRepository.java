package org.raul.receipesweb.repository;

import org.raul.receipesweb.model.RecipeStep;
import org.raul.receipesweb.utils.RecipeStepKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, RecipeStepKey> {
}
