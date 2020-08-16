package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.user.User;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.DietaryRequirement;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.FoodGroup;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.VeganDietaryRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class VeganUserHealthTest {
    Set<DietaryRequirement> veganDietaryRequirement;

    @BeforeEach
    void setUp() {
        veganDietaryRequirement = Set.of(new VeganDietaryRequirement());
    }

    @Test
    public void with_normal_weight_is_healthy_if_its_favorites_fruits_include_at_least_two_fruits() {
        Food apple = new Food("apple", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        Food orange = new Food("orange", FoodGroup.VEGETABLES_FRUITS_SEED, Set.of());
        User aHealthyVegan = UserObjectMother.aRandomUserWith()
                                             .dateOfBirth(LocalDate.of(2010, 12, 4))
                                             .favoriteFoods(Set.of(apple, orange))
                                             .weight(91)
                                             .height(1.8)
                                             .dietaryRequirements(veganDietaryRequirement)
                                             .build();
        assertThat(aHealthyVegan.isHealthy()).isEqualTo(true);
    }
}
