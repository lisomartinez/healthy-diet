package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.domain.Routine;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.exceptions.DiabeticHasNotFavoriteFoodUserException;
import com.github.lmartinez84.healthydiet.utils.DoubleComparerUtils;

public class DiabeticDietaryRequirement implements DietaryRequirement {

    public static final int MAX_HEALTHY_WEIGHT = 70;

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
