package com.github.lmartinez84.healthydiet.domain.user.exceptions;

public class NoHeightUserException extends RuntimeException {
    public NoHeightUserException() {
        super("User height should be greater than zero");
    }
}
