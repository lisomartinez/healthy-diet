package com.github.lmartinez84.healthydiet.repositories;

public class FoodNotExistException extends RuntimeException {
    public FoodNotExistException(FoodId foodId) {
        super("Food with id " + foodId.asString() + " does not exist");
    }
}
