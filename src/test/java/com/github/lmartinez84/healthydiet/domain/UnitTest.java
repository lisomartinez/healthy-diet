package com.github.lmartinez84.healthydiet.domain;

import com.github.lmartinez84.healthydiet.recipes.domain.recipe.ingredient.Unit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnitTest {

    @Test
    void same_unit_are_equals() {
        assertThat(Unit.KILOGRAMS).isEqualTo(Unit.KILOGRAMS);

    }

    @Test
    void different_units_are_not_equals() {
        assertThat(Unit.KILOGRAMS).isNotEqualTo(Unit.GRAMS);
    }

    @Test
    void testHashCode() {
        assertThat(Unit.KILOGRAMS).hasSameHashCodeAs(Unit.KILOGRAMS);
        assertThat(Unit.KILOGRAMS.hashCode()).isNotEqualTo(Unit.GRAMS.hashCode());
    }
}