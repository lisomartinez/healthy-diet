package com.github.lmartinez84.healthydiet.user.domain.dietary_requirement;

import com.github.lmartinez84.healthydiet.shared.domain.FoodInadequacy;
import com.github.lmartinez84.healthydiet.user.domain.Routine;
import com.github.lmartinez84.healthydiet.user.domain.User;
import com.github.lmartinez84.healthydiet.user.domain.exceptions.HypertensiveHasNotFavoriteFoodUserException;

public class HypertensiveDietaryRequirement implements DietaryRequirement {

    private static final FoodInadequacy inadecuacy = FoodInadequacy.HYPERTENSIVE;

    @Override
    public FoodInadequacy inadequacy() {
        return inadecuacy;
    }

    @Override
    public boolean isCompensated(User user) {
        return user.routine() == Routine.INTENSIVE;
    }

    @Override
    public void validate(User user) {
        if (user.favoritesFoods().isEmpty()) {
            throw new HypertensiveHasNotFavoriteFoodUserException();
        }
    }


}
