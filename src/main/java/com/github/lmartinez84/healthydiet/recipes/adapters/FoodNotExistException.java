package com.github.lmartinez84.healthydiet.recipes.adapters;

import com.github.lmartinez84.healthydiet.recipes.domain.food.FoodId;

public class FoodNotExistException extends RuntimeException {
    public FoodNotExistException(FoodId foodId) {
        super("Food with id " + foodId.asString() + " does not exist");
    }
}
