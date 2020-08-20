package com.github.lmartinez84.healthydiet.shared.domain;

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
