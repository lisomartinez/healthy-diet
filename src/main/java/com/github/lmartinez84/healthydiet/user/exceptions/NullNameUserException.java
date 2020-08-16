package com.github.lmartinez84.healthydiet.user.exceptions;

public class NullNameUserException extends RuntimeException {
    public NullNameUserException() {
        super("User name cannot be blank");
    }
}
