package com.github.lmartinez84.healthydiet.domain.recipe;

public class InvalidNumberOfIngredientsRecipeException extends RuntimeException {
    public InvalidNumberOfIngredientsRecipeException() {
        super("The minimum quantity of ingredients is one but the recipe contains none");
    }
}