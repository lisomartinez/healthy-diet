package com.github.lmartinez84.healthydiet.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.Routine;
import com.github.lmartinez84.healthydiet.user.User;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.exceptions.HypertensiveHasNotFavoriteFoodUserException;

public class HypertensiveDietaryRequirement implements DietaryRequirement {
    @Override
    public boolean isCompensated(User user) {
        return user.routine().equals(Routine.INTENSIVE);
    }

    @Override
    public void validate(User user) {
        if (user.favoritesFoods().size() < 1) {
            throw new HypertensiveHasNotFavoriteFoodUserException();
        }
    }


}
