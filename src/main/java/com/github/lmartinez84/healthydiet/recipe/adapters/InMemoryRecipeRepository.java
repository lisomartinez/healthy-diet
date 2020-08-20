package com.github.lmartinez84.healthydiet.recipe.adapters;

import com.github.lmartinez84.healthydiet.recipe.Recipe;
import com.github.lmartinez84.healthydiet.recipe.RecipeId;
import com.github.lmartinez84.healthydiet.recipe.RecipeRepository;
import com.github.lmartinez84.healthydiet.recipe.ingredient.Ingredient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InMemoryRecipeRepository implements RecipeRepository {
    private Map<RecipeId, Recipe> recipes;

    public InMemoryRecipeRepository() {
        recipes = new LinkedHashMap<>();
    }

    @Override
    public Mono<Recipe> create(Recipe entity) {
        entity.id(RecipeId.of(UUID.randomUUID().toString()));
        recipes.putIfAbsent(entity.id(), entity);
        return Mono.just(entity);
    }

    @Override
    public Mono<Void> delete(Recipe entity) {
        Recipe deleted = recipes.remove(entity.id());
        if (deleted == null) {
            return Mono.error(new RecipeNotExistException(entity.id()));
        }
        return Mono.empty();
    }

    @Override
    public Mono<Recipe> update(Recipe entity) {
        Recipe actualizado = recipes.computeIfPresent(entity.id(), (recipeId, recipe) -> recipe);
        if (actualizado == null) {
            return Mono.error(new RecipeNotExistException(entity.id()));
        }
        return Mono.just(actualizado);
    }

    @Override
    public Mono<Recipe> getById(RecipeId identifier) {
        return Mono.justOrEmpty(recipes.get(identifier));
    }

    @Override
    public Flux<Recipe> search(String value) {
        if (isValueInvalid(value)) {
            return Flux.empty();
        }
        Stream<Recipe> matchingRecipes = recipes.values()
                                                .stream()
                                                .filter(recipeMatchingCriterias(value));
        return Flux.fromStream(matchingRecipes);
    }


    private Predicate<Recipe> recipeMatchingCriterias(String value) {
        Predicate<Recipe> recipeNamePartiallyMatchValue = recipe -> recipe.name().startsWith(value);
        Predicate<Ingredient> ingredientNameStartWithValue = ingredient -> ingredient.name().startsWith(value);
        Predicate<Recipe> ingredientsPartiallyMatchIngredientsName =
                recipe -> recipe.ingredients().stream().anyMatch(ingredientNameStartWithValue);
        return recipeNamePartiallyMatchValue.or(ingredientsPartiallyMatchIngredientsName);
    }

    private boolean isValueInvalid(String value) {
        return value == null || value.isBlank();
    }

    @Override
    public Flux<Recipe> findBy(Predicate<Recipe> criteria) {
        return Flux.fromStream(recipes.values().stream().filter(criteria));
    }
}
