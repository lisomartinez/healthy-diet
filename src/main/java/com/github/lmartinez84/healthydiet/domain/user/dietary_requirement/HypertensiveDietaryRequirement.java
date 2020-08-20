package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.domain.food.FoodInadequacy;
import com.github.lmartinez84.healthydiet.domain.user.Routine;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.exceptions.HypertensiveHasNotFavoriteFoodUserException;

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
