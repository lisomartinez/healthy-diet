package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.domain.recipe.RecipeId;

public class RecipeNotExistException extends RuntimeException {
    public RecipeNotExistException(RecipeId recipeId) {
        super("Recipe with id " + recipeId.asString() + " does not exist");
    }
}
