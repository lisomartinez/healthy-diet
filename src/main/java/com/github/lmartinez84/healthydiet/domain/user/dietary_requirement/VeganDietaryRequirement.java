package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.domain.user.User;

public class VeganDietaryRequirement implements DietaryRequirement {

    public static final int REQUIRED_NUMBER_OF_FRUITS = 2;

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
                   .count() >= REQUIRED_NUMBER_OF_FRUITS;
    }
}
