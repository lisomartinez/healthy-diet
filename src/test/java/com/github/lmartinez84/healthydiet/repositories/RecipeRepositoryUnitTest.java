package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.recipes.adapters.InMemoryRecipeRepository;
import com.github.lmartinez84.healthydiet.recipes.domain.recipe.Recipe;
import com.github.lmartinez84.healthydiet.recipes.domain.recipe.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)

public abstract class RecipeRepositoryUnitTest {

    protected RecipeRepository recipeRepository;

    @BeforeEach
    protected void setUp() {
        recipeRepository = new InMemoryRecipeRepository();
    }

    protected void assertRecipesAreEqual(Recipe aRecipe, Recipe copiedRecipe) {
        assertThat(copiedRecipe.author()).isEqualTo(aRecipe.author());
        assertThat(copiedRecipe.name()).isEqualTo(aRecipe.name());
        assertThat(copiedRecipe.collaborators()).containsExactlyElementsOf(aRecipe.collaborators());
        assertThat(copiedRecipe.calories()).isEqualTo(aRecipe.calories());
        assertThat(copiedRecipe.ingredients()).containsExactlyElementsOf(aRecipe.ingredients());
        assertThat(copiedRecipe.steps()).containsExactlyElementsOf(aRecipe.steps());
    }
}
