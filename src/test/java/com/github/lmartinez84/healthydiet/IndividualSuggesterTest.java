package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.recipe.adapters.InMemoryRecipeRepository;
import com.github.lmartinez84.healthydiet.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reactor.test.StepVerifier;

import static com.github.lmartinez84.healthydiet.RecipeObjectMother.*;
import static com.github.lmartinez84.healthydiet.UserObjectMother.*;

@Execution(ExecutionMode.CONCURRENT)
class IndividualSuggesterTest extends SuggesterTest {

    @Override
    protected void populateRepository(InMemoryRecipeRepository recipeRepository) {
        recipeRepository.create(createEggplantTomatoBake(createRandomUser()));
        recipeRepository.create(createChickeParmesan(createRandomUser()));
        recipeRepository.create(createFriedChickenRecipe(createRandomUser()));
    }

    @Test
    void should_return_an_empty_flux_when_called_with_null() {
        StepVerifier.create(suggester.getSuggestionFor((User) null))
                    .verifyComplete();
    }

    @Test
    void should_return_an_empty_flux_when_has_not_recipes() {
        User user = hypertensiveUser();
        user.addDislikedFood(FoodObjectMother.createEggplant());
        user.addDislikedFood(FoodObjectMother.createChicken());

        StepVerifier.create(suggester.getSuggestionFor(user))
                    .verifyComplete();
    }

    @Test
    void should_return_recipes_with_ingredients_not_disliked_by_user() {
        User user = hypertensiveUser();
        user.addDislikedFood(FoodObjectMother.createEggplant());

        StepVerifier.create(suggester.getSuggestionFor(user))
                    .assertNext(recipe -> assertNotContainsDisliked(recipe, user))
                    .assertNext(recipe -> assertNotContainsDisliked(recipe, user))
                    .verifyComplete();
    }

    @Test
    void should_return_recipes_without_inadequacies() {
        User user = veganUser();
        StepVerifier.create(suggester.getSuggestionFor(user))
                    .assertNext(recipe -> assertNotContainsInadequacies(recipe, user))
                    .verifyComplete();
    }

    @Test
    void should_return_recipes_without_inadequacies_and_disliked_food() {
        User user = veganUser();
        user.addDislikedFood(FoodObjectMother.createEggplant());
        StepVerifier.create(suggester.getSuggestionFor(user))
                    .verifyComplete();
    }
}
