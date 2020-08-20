package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.domain.recipe.FoodId;

public class FoodNotExistException extends RuntimeException {
    public FoodNotExistException(FoodId foodId) {
        super("Food with id " + foodId.asString() + " does not exist");
    }
}
