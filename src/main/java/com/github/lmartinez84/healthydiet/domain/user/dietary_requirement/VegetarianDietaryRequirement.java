package com.github.lmartinez84.healthydiet.domain.user.dietary_requirement;

import com.github.lmartinez84.healthydiet.domain.user.User;

public class VegetarianDietaryRequirement implements DietaryRequirement {
    @Override
    public boolean isCompensated(User user) {
        return isUnderThirty(user) || favoriteFoodsDoNotContainFats(user);
    }

    @Override
    public void validate(User user) {

    }


    private boolean favoriteFoodsDoNotContainFats(User user) {
        return user.favoritesFoods().stream()
                   .noneMatch(food -> food.isMemberOfGroup(FoodGroup.OIL_FAT_SUGAR));
    }

    private boolean isUnderThirty(User user) {
        return user.age() < 30;
    }
}
