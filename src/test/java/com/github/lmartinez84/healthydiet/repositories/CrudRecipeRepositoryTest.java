package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.RecipeObjectMother;
import com.github.lmartinez84.healthydiet.recipes.adapters.RecipeNotExistException;
import com.github.lmartinez84.healthydiet.recipes.domain.recipe.Recipe;
import com.github.lmartinez84.healthydiet.recipes.domain.recipe.RecipeId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reactor.test.StepVerifier;

import java.util.UUID;

@Execution(ExecutionMode.CONCURRENT)
class CrudRecipeRepositoryTest extends RecipeRepositoryUnitTest {

    private Recipe recipe;


    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        recipe = RecipeObjectMother.createRandomRecipe();

        StepVerifier.create(recipeRepository.create(recipe))
                    .assertNext(saved -> assertRecipesAreEqual(recipe, saved))
                    .verifyComplete();
    }


    @Test
    void update_should_update_recipe_in_repo() {
        recipe.name("Sarasa");
        StepVerifier.create(recipeRepository.update(recipe))
                    .assertNext(saved -> assertRecipesAreEqual(recipe, saved))
                    .verifyComplete();
    }

    @Test
    void update_should_throw_an_RecipeNotExistException() {
        recipe.id(RecipeId.of(UUID.randomUUID().toString()));

        StepVerifier.create(recipeRepository.update(recipe))
                    .expectError(RecipeNotExistException.class)
                    .verify();
    }

    @Test
    void delete_should_delete_recipe_from_repo_if_exists() {
        StepVerifier.create(recipeRepository.delete(recipe))
                    .verifyComplete();

        StepVerifier.create(recipeRepository.getById(recipe.id()))
                    .verifyComplete();
    }

    @Test
    void delete_should_throwRecipeNotExistException_if_not_present_in_repo() {
        recipe.id(RecipeId.of(UUID.randomUUID().toString()));

        StepVerifier.create(recipeRepository.delete(recipe))
                    .expectError(RecipeNotExistException.class)
                    .verify();
    }

    @Test
    void getById_should_return_entity_if_present() {
        StepVerifier.create(recipeRepository.getById(recipe.id()))
                    .assertNext(retrieved -> assertRecipesAreEqual(recipe, retrieved))
                    .verifyComplete();
    }

    @Test
    void getById_should_return_an_empty_mono_if_not_present() {
        RecipeId recipeId = RecipeId.of(UUID.randomUUID().toString());

        StepVerifier.create(recipeRepository.getById(recipeId))
                    .verifyComplete();
    }

}