package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.UserObjectMother;
import com.github.lmartinez84.healthydiet.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reactor.test.StepVerifier;

import static com.github.lmartinez84.healthydiet.RecipeObjectMother.*;

@Execution(ExecutionMode.CONCURRENT)
class SearchRecipeRepositoryTest extends RecipeRepositoryUnitTest {

    public static final String NOT_MATCH = "other";
    private static final String EMPTY = "";
    private final User USER = UserObjectMother.createRandomUser();


    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();
        recipeRepository.create(createFriedChickenRecipe(USER));
        recipeRepository.create(createEggplantTomatoBake(USER));
        recipeRepository.create(createChickeParmesan(USER));
    }

    @Test
    void should_return_an_empty_flux_if_empty_value() {
        StepVerifier.create(recipeRepository.search(EMPTY))
                    .verifyComplete();
    }

    @Test
    void search_should_return_an_empty_flux_if_no_matching() {
        StepVerifier.create(recipeRepository.search(NOT_MATCH))
                    .verifyComplete();
    }

    @Test
    void search_should_return_an_empty_flux_if_no_value_null() {
        StepVerifier.create(recipeRepository.search(null))
                    .verifyComplete();
    }

    @Test
    void search_should_return_entities_fully_matching_name() {
        StepVerifier.create(recipeRepository.search(FRIED_CHICKEN))
                    .assertNext(found -> assertRecipesAreEqual(createFriedChickenRecipe(USER), found))
                    .verifyComplete();
    }

    @Test
    void search_should_return_entities_partially_matching_name() {
        StepVerifier.create(recipeRepository.search(FRIED))
                    .assertNext(found -> assertRecipesAreEqual(createFriedChickenRecipe(USER), found))
                    .verifyComplete();
    }

    @Test
    void search_should_return_entities_fully_matching_ingredient_name() {
        StepVerifier.create(recipeRepository.search(CHICKEN))
                    .assertNext(found -> assertRecipesAreEqual(createFriedChickenRecipe(USER), found))
                    .assertNext(found -> assertRecipesAreEqual(createChickeParmesan(USER), found))
                    .verifyComplete();
    }

    @Test
    void search_should_return_entities_partially_matching_ingredient_name() {
        StepVerifier.create(recipeRepository.search(CHICKEN_FIRST_THREE_CHARS))
                    .assertNext(found -> assertRecipesAreEqual(createFriedChickenRecipe(USER), found))
                    .assertNext(found -> assertRecipesAreEqual(createChickeParmesan(USER), found))
                    .verifyComplete();
    }
}
