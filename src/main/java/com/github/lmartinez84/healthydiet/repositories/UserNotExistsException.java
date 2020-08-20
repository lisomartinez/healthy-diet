package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.domain.user.UserId;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(UserId id) {
        super("User with id " + id.asString() + " does not exist");
    }
}
