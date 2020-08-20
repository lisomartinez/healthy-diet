package com.github.lmartinez84.healthydiet.domain.user;

public class NullNameUserException extends RuntimeException {
    public NullNameUserException() {
        super("User name cannot be blank");
    }
}
