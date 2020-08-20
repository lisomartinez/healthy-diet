package com.github.lmartinez84.healthydiet.domain.user;

public class NoWeightUserException extends RuntimeException {
    public NoWeightUserException() {
        super("User weight should be greater than zero");
    }
}
