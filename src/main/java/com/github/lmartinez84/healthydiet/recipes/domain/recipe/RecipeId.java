package com.github.lmartinez84.healthydiet.recipes.domain.recipe;

import com.github.lmartinez84.healthydiet.shared.domain.Identifier;

public class RecipeId extends Identifier {
    public RecipeId(String value) {
        super(value);
    }

    public static RecipeId of(String value) {
        return new RecipeId(value);
    }

    public static RecipeId nullId() {
        return new RecipeId(Identifier.EMPTY);
    }
}
