package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.recipe.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class IngredientTest {
    @Test
    void an_ingredient_can_have_numeric_quantity() {
        Ingredient anIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                 NumericQuantity.of(1.5, Unit.KILOGRAMS));

        assertThat(anIngredient.quantity())
                .isEqualTo(NumericQuantity.of(1.5, Unit.KILOGRAMS));
    }

    @Test
    void an_ingredient_can_have_description_quantity() {
        Ingredient anIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                 DescriptionQuantity.of("aDescription"));

        assertThat(anIngredient.quantity())
                .isEqualTo(DescriptionQuantity.of("aDescription"));
    }

    @Test
    void numeric_quantity_and_description_quantity_are_not_comparable() {
        Ingredient anIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                 DescriptionQuantity.of("aDescription"));

        assertThat(anIngredient.quantity())
                .isNotEqualTo(NumericQuantity.of(1.5, Unit.KILOGRAMS));
    }

    @Test
    void equals_food_are_equals_ingredients() {
        Ingredient anIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                 DescriptionQuantity.of("aDescription"));
        Ingredient anSecondIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                       DescriptionQuantity.of("aDescription"));
        assertThat(anIngredient).isEqualTo(anSecondIngredient);
    }

    @Test
    void different_food_are_not_equals_ingredients() {
        Ingredient anIngredient = new Ingredient(new Food("apple", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                 DescriptionQuantity.of("aDescription"));
        Ingredient anSecondIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                       DescriptionQuantity.of("aDescription"));
        assertThat(anIngredient).isNotEqualTo(anSecondIngredient);
    }

    @Test
    void equals_food_has_same_hashcode() {
        Ingredient anIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                 DescriptionQuantity.of("aDescription"));
        Ingredient anSecondIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                       DescriptionQuantity.of("aDescription"));

        assertThat(anIngredient)
                .isEqualTo(anSecondIngredient)
                .hasSameHashCodeAs(anSecondIngredient);
    }

    @Test
    void different_food_has_same_hashcode() {
        Ingredient anIngredient = new Ingredient(new Food("apple", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                 DescriptionQuantity.of("aDescription"));
        Ingredient anSecondIngredient = new Ingredient(new Food("potato", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of()),
                                                       DescriptionQuantity.of("aDescription"));
        assertThat(anIngredient).isNotEqualTo(anSecondIngredient);

        assertThat(anIngredient.hashCode()).isNotEqualTo(anSecondIngredient.hashCode());

    }
}
