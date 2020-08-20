package com.github.lmartinez84.healthydiet.user.domain.exceptions;

public class NoBirthDatetUserException extends RuntimeException {
    public NoBirthDatetUserException() {
        super("User should have a birth date");
    }
}
