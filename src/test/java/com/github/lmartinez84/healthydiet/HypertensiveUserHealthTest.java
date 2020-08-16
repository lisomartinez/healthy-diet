package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.Food;
import com.github.lmartinez84.healthydiet.domain.Routine;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.DietaryRequirement;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.FoodGroup;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.HypertensiveDietaryRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class HypertensiveUserHealthTest {
    Set<DietaryRequirement> hypertensiveDietaryRequirement;

    @BeforeEach
    void setUp() {
        hypertensiveDietaryRequirement = Set.of(new HypertensiveDietaryRequirement());
    }

    @Test
    public void is_healthy_if_its_routine_is_intensive() {
        User aHealthyHypertensive = UserObjectMother.aRandomUserWith()
                                                    .weight(91)
                                                    .height(1.8)
                                                    .dietaryRequirements(hypertensiveDietaryRequirement)
                                                    .routine(Routine.INTENSIVE)
                                                    .favoriteFoods(Set.of(new Food("potatos",
                                                                                   FoodGroup.VEGETABLES_FRUITS_SEED,
                                                                                   Set.of())))
                                                    .build();
        assertThat(aHealthyHypertensive.isHealthy()).isEqualTo(true);
    }

    @Test
    public void is_unhealthy_if_its_routine_is_not_intensive() {
        User anUnhealthyHypertensive = UserObjectMother.aRandomUserWith()
                                                       .weight(91)
                                                       .height(1.8)
                                                       .dietaryRequirements(hypertensiveDietaryRequirement)
                                                       .favoriteFoods(Set.of(new Food("potatos",
                                                                                      FoodGroup.VEGETABLES_FRUITS_SEED,
                                                                                      Set.of())))
                                                       .routine(Routine.ACTIVE)
                                                       .build();
        assertThat(anUnhealthyHypertensive.isHealthy()).isEqualTo(false);
    }

}
