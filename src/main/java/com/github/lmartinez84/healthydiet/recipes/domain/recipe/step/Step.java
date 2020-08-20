package com.github.lmartinez84.healthydiet.recipes.domain.recipe.step;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Step step = (Step) o;
        return description.equals(step.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
