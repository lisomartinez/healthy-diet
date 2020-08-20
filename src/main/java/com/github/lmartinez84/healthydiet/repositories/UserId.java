package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.domain.Identifier;

import javax.validation.constraints.NotNull;

public class UserId extends Identifier {
    protected UserId(@NotNull(message = "id cannot be null") String value) {
        super(value);
    }

    public static UserId nullId() {
        return new UserId(Identifier.EMPTY);
    }

    public static UserId of(String id) {
        return new UserId(id);
    }
}
