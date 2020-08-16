package com.github.lmartinez84.healthydiet.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.user.User;

public class CeliacDietaryRequirement implements DietaryRequirement {
    @Override
    public boolean isCompensated(User user) {
        return true;
    }

    @Override
    public void validate(User user) {

    }
}
