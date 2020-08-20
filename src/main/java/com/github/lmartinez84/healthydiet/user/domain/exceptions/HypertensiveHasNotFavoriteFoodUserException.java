package com.github.lmartinez84.healthydiet.user.domain.exceptions;

public class HypertensiveHasNotFavoriteFoodUserException extends RuntimeException {
    public HypertensiveHasNotFavoriteFoodUserException() {
        super("Hypertensive user should have at least one favoriteFood");
    }
}
