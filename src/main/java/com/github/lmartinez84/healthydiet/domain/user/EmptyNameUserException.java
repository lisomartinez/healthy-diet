package com.github.lmartinez84.healthydiet.domain.user;

public class EmptyNameUserException extends RuntimeException {
    public EmptyNameUserException() {
        super("User name cannot be empty");
    }
}
