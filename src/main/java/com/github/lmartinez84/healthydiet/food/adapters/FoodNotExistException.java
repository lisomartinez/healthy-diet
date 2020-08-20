package com.github.lmartinez84.healthydiet.food.adapters;

import com.github.lmartinez84.healthydiet.food.FoodId;

public class FoodNotExistException extends RuntimeException {
    public FoodNotExistException(FoodId foodId) {
        super("Food with id " + foodId.asString() + " does not exist");
    }
}
