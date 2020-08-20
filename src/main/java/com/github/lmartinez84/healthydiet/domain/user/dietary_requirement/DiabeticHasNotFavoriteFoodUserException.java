package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement;

public class DiabeticHasNotFavoriteFoodUserException extends RuntimeException {
    public DiabeticHasNotFavoriteFoodUserException() {
        super("Diabetic user should have at least one favorite food");
    }
}
