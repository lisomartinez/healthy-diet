package com.github.lmartinez84.healthydiet.domain.recipe;

public enum FoodInadequacy {
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    HYPERTENSIVE("Hypertensive"),
    DIABETIC("Diabetic"),
    CELIAC("Celiac");

    private final String name;

    FoodInadequacy(String name) {
        this.name = name;
    }
}
