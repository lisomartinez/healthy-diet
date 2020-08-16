package com.github.lmartinez84.healthydiet;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Unit {
    public static final Unit KILOGRAMS = new Unit("Kg.");
    private final String value;

    private Unit(@NotNull(message = "unit cannot be null") String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return value.equals(unit.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}