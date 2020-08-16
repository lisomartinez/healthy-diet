package com.github.lmartinez84.healthydiet.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.user.User;

public interface DietaryRequirement {
    boolean isCompensated(User user);

    void validate(User user);
}
