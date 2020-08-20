package com.github.lmartinez84.healthydiet.user.domain;

public class EmptyNameUserException extends RuntimeException {
    public EmptyNameUserException() {
        super("User name cannot be empty");
    }
}
