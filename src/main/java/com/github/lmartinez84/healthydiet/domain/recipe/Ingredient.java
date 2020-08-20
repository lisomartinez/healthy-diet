package com.github.lmartinez84.healthydiet.domain.recipe;

import java.util.Objects;
import java.util.Set;

public class Ingredient {
    private final Food food;
    private final Quantity quantity;

    public Ingredient(Food food, Quantity quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return food.equals(that.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food);
    }

    public Quantity quantity() {
        return quantity;
    }

    public Set<FoodInadequacy> inadequateFor() {
        return food.isInadequateFor();
    }

    public Ingredient copy() {
        return new Ingredient(food.copy(), quantity);
    }

    public String name() {
        return food.name();
    }
}
