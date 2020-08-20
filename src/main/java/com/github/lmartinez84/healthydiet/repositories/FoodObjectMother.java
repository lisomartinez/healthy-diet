package com.github.lmartinez84.healthydiet.repositories;

import com.github.lmartinez84.healthydiet.domain.Food;
import com.github.lmartinez84.healthydiet.domain.FoodInadequacy;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.FoodGroup;

import java.util.Set;

public final class FoodObjectMother {
    public static final String POTATO = "potato";
    public static final String POT = "pot";

    private FoodObjectMother() {
    }

    public static Food createRandomFood() {
        return new Food("chicken", FoodGroup.MEAT_FISH_EGGS, Set.of(FoodInadequacy.VEGAN, FoodInadequacy.VEGETARIAN));
    }

    public static Food createPotato() {
        return new Food(POTATO, FoodGroup.VEGETABLES_FRUITS_SEED, Set.of(FoodInadequacy.CELIAC));
    }
}
