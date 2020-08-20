package com.github.lmartinez84.healthydiet.user.domain.dietary_requirement;

import com.github.lmartinez84.healthydiet.shared.domain.FoodInadequacy;
import com.github.lmartinez84.healthydiet.user.domain.User;

public interface DietaryRequirement {
    FoodInadequacy inadequacy();

    boolean isCompensated(User user);

    void validate(User user);
}
