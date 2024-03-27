package org.raul.receipesweb.repository;

import org.raul.receipesweb.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
