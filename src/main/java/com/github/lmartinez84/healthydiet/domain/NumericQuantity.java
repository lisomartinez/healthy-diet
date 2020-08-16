package com.github.lmartinez84.healthydiet.domain;

import java.util.Objects;

public class NumericQuantity implements Quantity {
    private final double quantity;
    private final Unit unit;

    private NumericQuantity(double quantity, Unit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public static NumericQuantity of(double quantity, Unit unit) {
        return new NumericQuantity(quantity, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumericQuantity that = (NumericQuantity) o;
        return Double.compare(that.quantity, quantity) == 0 &&
                unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, unit);
    }
}
