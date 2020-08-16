package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.*;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.FoodGroup;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
}
