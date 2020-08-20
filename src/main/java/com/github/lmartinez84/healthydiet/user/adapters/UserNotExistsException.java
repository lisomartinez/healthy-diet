package com.github.lmartinez84.healthydiet.user.adapters;

import com.github.lmartinez84.healthydiet.user.domain.UserId;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(UserId id) {
        super("User with id " + id.asString() + " does not exist");
    }
}
