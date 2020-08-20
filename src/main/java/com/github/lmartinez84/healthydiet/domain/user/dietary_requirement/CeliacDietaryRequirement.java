package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.domain.food.FoodInadequacy;
import com.github.lmartinez84.healthydiet.domain.user.User;

public class CeliacDietaryRequirement implements DietaryRequirement {

    private static final FoodInadequacy inadequacy = FoodInadequacy.CELIAC;

    @Override
    public FoodInadequacy inadequacy() {
        return inadequacy;
    }

    @Override
    public boolean isCompensated(User user) {
        return true;
    }

    @Override
    public void validate(User user) {

    }
}
