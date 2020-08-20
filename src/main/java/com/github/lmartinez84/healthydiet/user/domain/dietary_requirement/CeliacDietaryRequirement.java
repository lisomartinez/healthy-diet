package com.github.lmartinez84.healthydiet.user.domain.dietary_requirement;

import com.github.lmartinez84.healthydiet.shared.domain.FoodInadequacy;
import com.github.lmartinez84.healthydiet.user.domain.User;

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
