package com.github.lmartinez84.healthydiet.domain.recipe.exceptions;

public class InvalidCaloriesRecipeException extends RuntimeException {
    public InvalidCaloriesRecipeException() {
        super("Calories of recipe should be between 10 and 5000");
    }
}
