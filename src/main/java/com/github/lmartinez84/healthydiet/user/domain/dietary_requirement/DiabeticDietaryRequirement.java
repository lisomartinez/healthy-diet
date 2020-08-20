package com.github.lmartinez84.healthydiet.user.domain.dietary_requirement;

import com.github.lmartinez84.healthydiet.food.FoodInadequacy;
import com.github.lmartinez84.healthydiet.shared.domain.DoubleComparerUtils;
import com.github.lmartinez84.healthydiet.user.domain.Routine;
import com.github.lmartinez84.healthydiet.user.domain.User;
import com.github.lmartinez84.healthydiet.user.domain.exceptions.DiabeticHasNotFavoriteFoodUserException;

public class DiabeticDietaryRequirement implements DietaryRequirement {

    public static final int MAX_HEALTHY_WEIGHT = 70;
    private static final FoodInadequacy inadecuacy = FoodInadequacy.DIABETIC;

    @Override
    public FoodInadequacy inadequacy() {
        return inadecuacy;
    }

    @Override
    public boolean isCompensated(User user) {
        return hasAnActiveRoutine(user) || weightUnderSeventy(user);
    }

    @Override
    public void validate(User user) {
        if (user.favoritesFoods().isEmpty()) {
            throw new DiabeticHasNotFavoriteFoodUserException();
        }
    }


    private boolean hasAnActiveRoutine(User user) {
        return user.routine() == Routine.ACTIVE;
    }

    private boolean weightUnderSeventy(User user) {
        return DoubleComparerUtils.isLessThanOrEqual(user.weight(), MAX_HEALTHY_WEIGHT);
    }
}
