package com.github.lmartinez84.healthydiet.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.user.User;

public class VeganDietaryRequirement implements DietaryRequirement {
    @Override
    public boolean isCompensated(User user) {
        return itsFavoritesFoodsIncludeAtLeastToFruits(user);
    }

    @Override
    public void validate(User user) {

    }


    private boolean itsFavoritesFoodsIncludeAtLeastToFruits(User user) {
        return user.favoritesFoods()
                   .stream()
                   .filter(food -> food.isMemberOfGroup(FoodGroup.VEGETABLES_FRUITS_SEED))
                   .count() >= 2;
    }
}
