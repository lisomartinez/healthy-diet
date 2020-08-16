package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.user.User;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.CeliacDietaryRequirement;
import com.github.lmartinez84.healthydiet.user.dietary_requirement.DietaryRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CeliacUserHealthTest {
    Set<DietaryRequirement> celiacDietaryRequirement;

    @BeforeEach
    void setUp() {
        celiacDietaryRequirement = Set.of(new CeliacDietaryRequirement());
    }

    @Test
    public void a_celiac_with_normal_weight_is_healthy() {
        User aHealthyCeliac = UserObjectMother.aRandomUserWith()
                                              .weight(91)
                                              .height(1.8)
                                              .dietaryRequirements(celiacDietaryRequirement)
                                              .build();
        assertThat(aHealthyCeliac.isHealthy()).isEqualTo(true);
    }

    @Test
    public void a_celiac_outside_normal_weight_is_unhealthy() {
        User aHealthyCeliac = UserObjectMother.aRandomUserWith()
                                              .weight(150)
                                              .height(1.8)
                                              .dietaryRequirements(celiacDietaryRequirement)
                                              .build();
        assertThat(aHealthyCeliac.isHealthy()).isEqualTo(false);
    }

}
