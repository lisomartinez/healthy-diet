package com.github.lmartinez84.healthydiet.domain;

import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.FoodGroup;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Food {
    private final String name;
    private final FoodGroup group;
    private final Set<FoodInadequacy> inadequacies;

    public Food(String name,
                FoodGroup group,
                Set<FoodInadequacy> inadequacies
    ) {
        this.name = name;
        this.group = group;
        this.inadequacies = inadequacies;
    }

    public Set<FoodInadequacy> isInadequateFor() {
        return Collections.unmodifiableSet(inadequacies);
    }

    public boolean isMemberOfGroup(FoodGroup group) {
        return this.group.equals(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
