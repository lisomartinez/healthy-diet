package com.github.lmartinez84.healthydiet;

import java.util.Objects;

public class DescriptionQuantity implements Quantity {
    private final String description;

    public DescriptionQuantity(String description) {
        this.description = description;
    }

    public static DescriptionQuantity of(String description) {
        return new DescriptionQuantity(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescriptionQuantity that = (DescriptionQuantity) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
