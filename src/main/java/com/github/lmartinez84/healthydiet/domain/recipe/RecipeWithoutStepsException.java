package com.github.lmartinez84.healthydiet.domain.recipe;

public class RecipeWithoutStepsException extends RuntimeException {
    public RecipeWithoutStepsException() {
        super("Recipe should have at least one step");
    }
}
