package com.github.lmartinez84.healthydiet.user.exceptions;

public class NoWeightUserException extends RuntimeException {
    public NoWeightUserException() {
        super("User weight should be greater than zero");
    }
}
