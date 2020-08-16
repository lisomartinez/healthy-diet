package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.domain.Routine;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.exceptions.DiabeticHasNotFavoriteFoodUserException;

public class DiabeticDietaryRequirement implements DietaryRequirement {
    @Override
    public boolean isCompensated(User user) {
        return hasAnActiveRoutine(user) || weightUnderSeventy(user);
    }

    @Override
    public void validate(User user) {
        if (user.favoritesFoods().size() < 1) {
            throw new DiabeticHasNotFavoriteFoodUserException();
        }
    }


    private boolean hasAnActiveRoutine(User user) {
        return user.routine().equals(Routine.ACTIVE);
    }

    private boolean weightUnderSeventy(User user) {
        return (user.weight() - 70) <= 0.00000001;
    }
}
