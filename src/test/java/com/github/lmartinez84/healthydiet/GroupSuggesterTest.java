package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.recipe.Recipe;
import com.github.lmartinez84.healthydiet.recipe.adapters.InMemoryRecipeRepository;
import com.github.lmartinez84.healthydiet.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reactor.test.StepVerifier;

import java.util.List;

import static com.github.lmartinez84.healthydiet.RecipeObjectMother.*;
import static com.github.lmartinez84.healthydiet.UserObjectMother.*;

@Execution(ExecutionMode.CONCURRENT)
public class GroupSuggesterTest extends SuggesterTest {

    private User randomAuthor;

    @Override
    protected void populateRepository(InMemoryRecipeRepository recipeRepository) {
        randomAuthor = createRandomUser();
        recipeRepository.create(createEggplantTomatoBake(randomAuthor));
        recipeRepository.create(createChickeParmesan(randomAuthor));
        recipeRepository.create(createFriedChickenRecipe(randomAuthor));
    }

    private void assertIsAdequateForAllUsers(List<User> group, Recipe recipe) {
        group.forEach(user -> assertNotContainsInadequacies(recipe, user));
    }

    @Test
    void group_with_one_person_applies_same_conditions_that_individual() {
        User user = hypertensiveUser();
        user.addDislikedFood(FoodObjectMother.createEggplant());
        user.addDislikedFood(FoodObjectMother.createChicken());

        StepVerifier.create(suggester.getSuggestionFor(List.of(user)))
                    .verifyComplete();
    }

    @Test
    void group_with_one_recipe_adequate_for_all_returns_one_recipe() {
        User vegan = veganUser();
        User vegetarian = vegatarianUser();
        List<User> group = List.of(vegan, vegetarian);

        StepVerifier.create(suggester.getSuggestionFor(group))
                    .assertNext(recipe -> assertIsAdequateForAllUsers(group, recipe))
                    .verifyComplete();
    }

    @Test
    void group_with_one_recipe_without_any_disliked_food() {
        User hypertensive = hypertensiveUser();
        hypertensive.addDislikedFood(FoodObjectMother.createChicken());

        User vegetarian = diabeticUser();
        vegetarian.addDislikedFood(FoodObjectMother.createEggplant());

        List<User> group = List.of(hypertensive, vegetarian);

        StepVerifier.create(suggester.getSuggestionFor(group))
                    .expectNext(createEggplantTomatoBake(randomAuthor))
                    .expectNext(createChickeParmesan(randomAuthor))
                    .verifyComplete();
    }
}
