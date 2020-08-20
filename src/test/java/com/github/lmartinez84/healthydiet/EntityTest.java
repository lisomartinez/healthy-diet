package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.recipe.Recipe;
import com.github.lmartinez84.healthydiet.domain.recipe.RecipeId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class EntityTest {
    @Test
    void an_entity_should_have_an_id() {
        Recipe recipe = RecipeObjectMother.createARecipeWithAuthor(UserObjectMother.createRandomUser(), Set.of());

        assertThat(recipe.id()).isEqualTo(RecipeId.nullId());
    }

    @Test
    void before_saved_entities_have_id_with_value_null() {
        Recipe recipe = RecipeObjectMother.createARecipeWithAuthor(UserObjectMother.createRandomUser(), Set.of());

        assertThat(recipe.id()).isEqualTo(RecipeId.nullId());
    }
}
