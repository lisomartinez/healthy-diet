package com.github.lmartinez84.healthydiet.domain.user;

import com.github.lmartinez84.healthydiet.domain.recipe.FoodInadequacy;

public interface DietaryRequirement {
    FoodInadequacy inadequacy();

    boolean isCompensated(User user);

    void validate(User user);
}
