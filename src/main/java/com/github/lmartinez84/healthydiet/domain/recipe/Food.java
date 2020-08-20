package com.github.lmartinez84.healthydiet.domain.recipe;

import com.github.lmartinez84.healthydiet.domain.Entity;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public final class Food extends Entity<FoodId> {
    private String name;
    private final FoodGroup group;
    private final Set<FoodInadequacy> inadequacies;

    public Food(String name,
                FoodGroup group,
                Set<FoodInadequacy> inadequacies
    ) {
        super(FoodId.nullId());
        this.name = name;
        this.group = group;
        this.inadequacies = inadequacies;
    }

    public Set<FoodInadequacy> isInadequateFor() {
        return Collections.unmodifiableSet(inadequacies);
    }

    public boolean isMemberOfGroup(FoodGroup group) {
        return this.group == group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Food copy() {
        return new Food(name, group, inadequacies);
    }

    public String name() {
        return this.name;
    }

    public FoodGroup group() {
        return group;
    }

    public void name(String name) {
        this.name = name;
    }
}
