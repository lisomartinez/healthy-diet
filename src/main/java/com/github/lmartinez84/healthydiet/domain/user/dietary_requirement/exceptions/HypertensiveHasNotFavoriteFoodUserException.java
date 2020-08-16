package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.exceptions;

public class HypertensiveHasNotFavoriteFoodUserException extends RuntimeException {
    public HypertensiveHasNotFavoriteFoodUserException() {
        super("Hypertensive user should have at least one favoriteFood");
    }
}