package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.food.Food;
import com.github.lmartinez84.healthydiet.food.FoodGroup;
import com.github.lmartinez84.healthydiet.food.FoodInadequacy;

import java.util.Set;

public final class FoodObjectMother {
    public static final String POTATO = "potato";
    public static final String POT = "pot";
    public static final String CHICKEN = "chicken";
    public static final String BACON = "bacon";
    public static final String EGGPLANT = "eggplant";

    private FoodObjectMother() {
    }

    public static Food createRandomFood() {
        return createChicken();
    }

    public static Food createPotato() {
        return new Food(POTATO, FoodGroup.VEGETABLES_FRUITS_SEED, Set.of(FoodInadequacy.CELIAC));
    }

    public static Food createBacon() {
        return new Food(BACON, FoodGroup.MEAT_FISH_EGGS, Set.of(FoodInadequacy.VEGAN, FoodInadequacy.VEGETARIAN));
    }

    public static Food createEggplant() {
        return new Food(EGGPLANT, FoodGroup.VEGETABLES_FRUITS_SEED, Set.of(FoodInadequacy.CELIAC));
    }

    public static Food createChicken() {
        return new Food(CHICKEN, FoodGroup.MEAT_FISH_EGGS, Set.of(FoodInadequacy.VEGAN, FoodInadequacy.VEGETARIAN));
    }
}
