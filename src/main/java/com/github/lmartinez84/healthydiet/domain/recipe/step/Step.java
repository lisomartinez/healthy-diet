package com.github.lmartinez84.healthydiet.domain.recipe.step;

public final class Step {
    private final String description;

    private Step(String description) {
        this.description = description;
    }

    public static Step of(String description) {
        return new Step(description);
    }

    public Step copy() {
        return new Step(description);
    }
}
