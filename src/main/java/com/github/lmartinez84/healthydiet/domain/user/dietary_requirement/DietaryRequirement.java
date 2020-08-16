package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.domain.user.User;

public interface DietaryRequirement {
    boolean isCompensated(User user);

    void validate(User user);
}
