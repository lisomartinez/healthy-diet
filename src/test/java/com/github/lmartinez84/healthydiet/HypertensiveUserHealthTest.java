package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.food.Food;
import com.github.lmartinez84.healthydiet.domain.food.FoodGroup;
import com.github.lmartinez84.healthydiet.domain.user.Routine;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.DietaryRequirement;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.HypertensiveDietaryRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class HypertensiveUserHealthTest {
    Set<DietaryRequirement> hypertensiveDietaryRequirement;

    @BeforeEach
    void setUp() {
        hypertensiveDietaryRequirement = Set.of(new HypertensiveDietaryRequirement());
    }

    @Test
    void is_healthy_if_its_routine_is_intensive() {
        User aHealthyHypertensive = UserObjectMother.aRandomUserWith()
                                                    .weight(91)
                                                    .height(1.8)
                                                    .dietaryRequirements(hypertensiveDietaryRequirement)
                                                    .routine(Routine.INTENSIVE)
                                                    .favoriteFoods(Set.of(new Food("potatos",
                                                                                   FoodGroup.VEGETABLES_FRUITS_SEED,
                                                                                   Set.of())))
                                                    .build();
        assertThat(aHealthyHypertensive.isHealthy()).isTrue();
    }

    @Test
    void is_unhealthy_if_its_routine_is_not_intensive() {
        User anUnhealthyHypertensive = UserObjectMother.aRandomUserWith()
                                                       .weight(91)
                                                       .height(1.8)
                                                       .dietaryRequirements(hypertensiveDietaryRequirement)
                                                       .favoriteFoods(Set.of(new Food("potatos",
                                                                                      FoodGroup.VEGETABLES_FRUITS_SEED,
                                                                                      Set.of())))
                                                       .routine(Routine.ACTIVE)
                                                       .build();
        assertThat(anUnhealthyHypertensive.isHealthy()).isFalse();
    }

}
