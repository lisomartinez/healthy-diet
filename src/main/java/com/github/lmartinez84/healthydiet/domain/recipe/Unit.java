package com.github.lmartinez84.healthydiet.domain.recipe;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public final class Unit {
    public static final Unit KILOGRAMS = new Unit("Kg.");
    public static final Unit GRAMS = new Unit("Gr.");
    public static final Unit UNIT = new Unit("Unit.");
    private final String value;

    private Unit(@NotNull(message = "unit cannot be null") String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Unit unit = (Unit) o;
        return value.equals(unit.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
