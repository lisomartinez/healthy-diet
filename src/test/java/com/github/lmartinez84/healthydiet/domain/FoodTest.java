package com.github.lmartinez84.healthydiet.domain;

import com.github.lmartinez84.healthydiet.recipes.domain.food.Food;
import com.github.lmartinez84.healthydiet.recipes.domain.food.FoodGroup;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class FoodTest {

    @Test
    void same_name_are_equals() {
        Food aFood = new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        Food otherFood = new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        assertThat(aFood).isEqualTo(otherFood);
    }

    @Test
    void same_name_have_same_hashcode() {
        Food aFood = new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        Food otherFood = new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        assertThat(aFood)
                .isEqualTo(otherFood)
                .hasSameHashCodeAs(otherFood);
    }

    @Test
    void different_name_are_not_equals() {
        Food aFood = new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        Food otherFood = new Food("apple", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        assertThat(aFood).isNotEqualTo(otherFood);
    }

    @Test
    void different_name_have_different_hashcode() {
        Food aFood = new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        Food otherFood = new Food("apple", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        assertThat(aFood).isNotEqualTo(otherFood);
        assertThat(aFood.hashCode()).isNotEqualTo(otherFood.hashCode());
    }
}