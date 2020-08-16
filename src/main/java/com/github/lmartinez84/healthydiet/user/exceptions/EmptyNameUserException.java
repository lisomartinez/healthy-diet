package com.github.lmartinez84.healthydiet.user.exceptions;

public class EmptyNameUserException extends RuntimeException {
    public EmptyNameUserException() {
        super("User name cannot be empty");
    }
}
