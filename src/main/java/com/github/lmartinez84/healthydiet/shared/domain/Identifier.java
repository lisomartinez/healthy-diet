package com.github.lmartinez84.healthydiet.shared.domain;

import javax.validation.constraints.NotNull;
import java.util.Objects;


public abstract class Identifier {
    public static final String EMPTY = "EMPTY";
    protected final String value;

    protected Identifier(@NotNull(message = "id cannot be null") String value) {
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
        Identifier identifier = (Identifier) o;
        return value.equals(identifier.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String asString() {
        return value;
    }
}
