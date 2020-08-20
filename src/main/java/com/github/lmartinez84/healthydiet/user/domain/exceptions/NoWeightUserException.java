package com.github.lmartinez84.healthydiet.user.domain.exceptions;

public class NoWeightUserException extends RuntimeException {
    public NoWeightUserException() {
        super("User weight should be greater than zero");
    }
}
