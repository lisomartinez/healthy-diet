package com.github.lmartinez84.healthydiet.domain.user.exceptions;

public class NoRoutineUserException extends RuntimeException {
    public NoRoutineUserException() {
        super("User should have a routine");
    }
}
