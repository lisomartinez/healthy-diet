package com.github.lmartinez84.healthydiet.user.domain.exceptions;

public class DiabeticHasNotFavoriteFoodUserException extends RuntimeException {
    public DiabeticHasNotFavoriteFoodUserException() {
        super("Diabetic user should have at least one favorite food");
    }
}
