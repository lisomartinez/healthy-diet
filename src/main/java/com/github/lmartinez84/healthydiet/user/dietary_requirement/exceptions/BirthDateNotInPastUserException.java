package com.github.lmartinez84.healthydiet.user.dietary_requirement.exceptions;

public class BirthDateNotInPastUserException extends RuntimeException {
    public BirthDateNotInPastUserException() {
        super("User birth date should be in the past");
    }
}
