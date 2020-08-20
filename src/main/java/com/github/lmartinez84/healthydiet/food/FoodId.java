package com.github.lmartinez84.healthydiet.food;

import com.github.lmartinez84.healthydiet.shared.domain.Identifier;

import javax.validation.constraints.NotNull;

public class FoodId extends Identifier {
    protected FoodId(@NotNull(message = "id cannot be null") String value) {
        super(value);
    }

    public static FoodId nullId() {
        return new FoodId(Identifier.EMPTY);
    }

    public static FoodId of(String id) {
        return new FoodId(id);
    }
}
