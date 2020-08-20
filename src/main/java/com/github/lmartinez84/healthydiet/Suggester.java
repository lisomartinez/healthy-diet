package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.food.Food;
import com.github.lmartinez84.healthydiet.food.FoodInadequacy;
import com.github.lmartinez84.healthydiet.recipe.Recipe;
import com.github.lmartinez84.healthydiet.recipe.RecipeRepository;
import com.github.lmartinez84.healthydiet.recipe.ingredient.Ingredient;
import com.github.lmartinez84.healthydiet.user.domain.User;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Suggester {
    private RecipeRepository recipeRepository;

    public Suggester(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Flux<Recipe> getSuggestionFor(User user) {
        if (user == null) {
            return Flux.empty();
        }
        return recipeRepository.findBy(notContainingDislikedOrInadequacies(user));
    }

    public Flux<Recipe> getSuggestionFor(List<User> users) {
        return recipeRepository.findBy(aggregatedIncompatibleRecipes(users))
                               .take(1)
                               .switchIfEmpty(oneRecipeCompatibleForEachUser(incompatiblesRecipes(users)));
    }

    protected Predicate<Recipe> aggregatedIncompatibleRecipes(List<User> users) {
        return incompatiblesRecipes(users)
                .reduce(Predicate::and)
                .orElseThrow();
    }

    protected Stream<Predicate<Recipe>> incompatiblesRecipes(List<User> users) {
        return users.stream()
                    .map(this::notContainingDislikedOrInadequacies);
    }

    protected Flux<Recipe> oneRecipeCompatibleForEachUser(Stream<Predicate<Recipe>> predicateStream) {
        return Flux.fromStream(predicateStream)
                   .flatMap(recipePredicate1 -> recipeRepository.findBy(recipePredicate1).take(1))
                   .distinct();
    }

    private Predicate<Recipe> notContainingDislikedOrInadequacies(User user) {
        return recipesNotContainingInadequacies(user)
                .and(recipesNotContainingDislikedFoods(user));

    }

    private Predicate<Recipe> recipesNotContainingDislikedFoods(User user) {
        Set<Food> dislikedFoods = user.dislikedFoods();
        return recipe -> recipe.ingredients().stream()
                               .map(Ingredient::food)
                               .noneMatch(dislikedFoods::contains);
    }

    private Predicate<Recipe> recipesNotContainingInadequacies(User user) {
        Set<FoodInadequacy> foodInadequacies = user.dietaryRequirements();
        return recipe -> recipe.inadequateFor()
                               .stream()
                               .noneMatch(foodInadequacies::contains);
    }
}
