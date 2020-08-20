package com.github.lmartinez84.healthydiet.recipe.exceptions;

public class RecipeWithoutStepsException extends RuntimeException {
    public RecipeWithoutStepsException() {
        super("Recipe should have at least one step");
    }
}
