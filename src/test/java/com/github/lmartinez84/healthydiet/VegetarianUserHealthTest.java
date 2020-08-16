package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.Food;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.DietaryRequirement;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.FoodGroup;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.VegetarianDietaryRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class VegetarianUserHealthTest {
    Set<DietaryRequirement> vegetarianDietaryRequirement;

    @BeforeEach
    void setUp() {
        vegetarianDietaryRequirement = Set.of(new VegetarianDietaryRequirement());
    }

    @Test
    public void with_normal_weight_is_healthy_if_age_is_less_than_30() {
        User aHealthyVegetarian = UserObjectMother.aRandomUserWith()
                                                  .dateOfBirth(LocalDate.of(2010, 12, 4))
                                                  .weight(91)
                                                  .height(1.8)
                                                  .dietaryRequirements(vegetarianDietaryRequirement)
                                                  .build();
        assertThat(aHealthyVegetarian.isHealthy()).isEqualTo(true);
    }

    @Test
    public void with_normal_weight_is_healthy_if_age_is_more_than_30_and_favorite_meal_dont_include_fats() {
        User aHealthyVegetarian = UserObjectMother.aRandomUserWith()
                                                  .dateOfBirth(LocalDate.of(1970, 12, 4))
                                                  .favoriteFoods(Set.of())
                                                  .weight(91)
                                                  .height(1.8)
                                                  .dietaryRequirements(vegetarianDietaryRequirement)
                                                  .build();
        assertThat(aHealthyVegetarian.isHealthy()).isEqualTo(true);
    }

    @Test
    public void with_normal_weight_is_unhealthy_if_age_is_more_than_30_and_favorite_foods_include_fats() {
        Food fattyFood = new Food("fried chicken", FoodGroup.OIL_FAT_SUGAR, Set.of());

        User anUnhealthyVegetarian = UserObjectMother.aRandomUserWith()
                                                     .dateOfBirth(LocalDate.of(1970, 12, 4))
                                                     .favoriteFoods(Set.of(fattyFood))
                                                     .weight(91)
                                                     .height(1.8)
                                                     .dietaryRequirements(vegetarianDietaryRequirement)
                                                     .build();
        assertThat(anUnhealthyVegetarian.isHealthy()).isEqualTo(false);
    }
}
