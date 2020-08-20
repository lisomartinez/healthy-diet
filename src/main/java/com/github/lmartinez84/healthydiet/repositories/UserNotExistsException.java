package com.github.lmartinez84.healthydiet.repositories;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(UserId id) {
        super("User with id " + id.asString() + " does not exist");
    }
}
