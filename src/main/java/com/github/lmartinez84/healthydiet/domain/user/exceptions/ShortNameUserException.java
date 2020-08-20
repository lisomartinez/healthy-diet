package com.github.lmartinez84.healthydiet.domain.user.exceptions;

public class ShortNameUserException extends RuntimeException {
    public ShortNameUserException() {
        super("User first name should have more than four characters");
    }
}
