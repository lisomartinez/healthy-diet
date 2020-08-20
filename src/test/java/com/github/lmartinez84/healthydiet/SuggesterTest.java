package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.food.Food;
import com.github.lmartinez84.healthydiet.food.FoodInadequacy;
import com.github.lmartinez84.healthydiet.recipe.Recipe;
import com.github.lmartinez84.healthydiet.recipe.adapters.InMemoryRecipeRepository;
import com.github.lmartinez84.healthydiet.recipe.ingredient.Ingredient;
import com.github.lmartinez84.healthydiet.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public abstract class SuggesterTest {
    protected Suggester suggester;
    protected InMemoryRecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        recipeRepository = new InMemoryRecipeRepository();
        suggester = new Suggester(recipeRepository);
        populateRepository(recipeRepository);
    }

    protected abstract void populateRepository(InMemoryRecipeRepository recipeRepository);

    protected void assertNotContainsInadequacies(Recipe recipe, User user) {
        Set<FoodInadequacy> foodInadequacies = recipe.inadequateFor();
        assertThat(foodInadequacies)
                .doesNotContainAnyElementsOf(user.dietaryRequirements());
    }

    protected void assertNotContainsDisliked(Recipe recipe, User user) {
        Set<Food> ingredients = recipe.ingredients().stream().map(Ingredient::food).collect(Collectors.toSet());
        assertThat(ingredients).isNotEmpty()
                               .doesNotContainAnyElementsOf(user.dislikedFoods());
    }
}
